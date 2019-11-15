package com.hyy.kcbweekly.weekly.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyy.kcbweekly.weekly.entity.TokenCode;
import com.hyy.kcbweekly.weekly.entity.UserInfo;
import com.hyy.kcbweekly.weekly.entity.VerificationCode;
import com.hyy.kcbweekly.weekly.mapper.TokeCodeMapper;
import com.hyy.kcbweekly.weekly.mapper.UserInfoMapper;
import com.hyy.kcbweekly.weekly.mapper.VerificationCodeMapper;
import com.hyy.kcbweekly.weekly.service.IUserInfoService;
import com.hyy.kcbweekly.weekly.util.CheckUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Resource
    private VerificationCodeMapper verificationCodeMapper;
    @Resource
    private TokeCodeMapper tokeCodeMapper;
    //通过电话验证码登录，返回token,
    @Override
    @Transactional
    public Map login(String mobile, String code) {
        VerificationCode verificationCode = verificationCodeMapper.findMobileCode(mobile, code);


        Map map = CheckUtil.checkMobileCode(verificationCode);
        //校验
        if(!map.get("code").equals("0")){
            return map;
        }
        TokenCode tokenCode = new TokenCode();
        tokenCode.setMobile(verificationCode.getMobileNumber());
        tokenCode.setToken(map.get("data").toString());
        tokenCode.setCreate_time(new Date());
        System.out.println(tokenCode);
        tokeCodeMapper.insert(tokenCode);
        return map;
    }
}
