package com.hyy.kcbweekly.weekly.service.impl;

import com.hyy.kcbweekly.weekly.entity.ResultInfo;
import com.hyy.kcbweekly.weekly.entity.VerificationCode;
import com.hyy.kcbweekly.weekly.service.IVerificationCodeService;
import com.hyy.kcbweekly.weekly.service.SmsSendCodeService;
import com.hyy.kcbweekly.weekly.util.AliyunMessageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class SmsSendCodeImpl implements SmsSendCodeService {
    @Resource
    AliyunMessageUtil aliyunMessageUtil;

    @Resource
    IVerificationCodeService iVerificationCodeService;

    @Override
    @Transactional
    public Integer SmsSendCode(String mobile) {
        //6位数的字符串
        String code = aliyunMessageUtil.createRandomNum(6);
        //判断此号码有没有发送验证码
        //已经发送验证码，判断时间
        VerificationCode verificationCodeByMobile = iVerificationCodeService.findVerificationCodeByMobile(mobile);
        if (verificationCodeByMobile != null) {
            //时间5分钟以内，已经发送,无须再次发送
            //发送验证码的时间
            long createTime = verificationCodeByMobile.getCreateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            //当前时间
            long time = new Date().getTime();
            long differ = time - createTime;
            if (differ < 5 * 1000 * 60L) {
                //表示不用再次发送
                return -2;
            }
            //大于5分钟重新发送,重新设置发送时间和验证码即可
            verificationCodeByMobile.setCreateTime(LocalDateTime.now());
            System.out.println(LocalDateTime.now());
            verificationCodeByMobile.setCode(code);
            iVerificationCodeService.updateById(verificationCodeByMobile);
            ResultInfo resultInfo = aliyunMessageUtil.sendMessage(mobile, code);
            if (resultInfo.getCode() == 0) {
                //发送成功
                return 0;
            } else {
                //发送失败
                return -1;
            }
        }
        //没有发送验证码，直接发送
        //将验证码存在数据
        VerificationCode verificationCode = new VerificationCode();

        verificationCode.setCreateTime(LocalDateTime.now());
        verificationCode.setCode(code);
        verificationCode.setMobileNumber(mobile);
        iVerificationCodeService.save(verificationCode);
        ResultInfo resultInfo = aliyunMessageUtil.sendMessage(mobile, code);
        if (resultInfo.getCode() == 0) {
            //发送成功
            return 0;
        } else {
            //发送失败
            return -1;
        }
    }


}
