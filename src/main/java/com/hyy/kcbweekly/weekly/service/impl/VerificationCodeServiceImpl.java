package com.hyy.kcbweekly.weekly.service.impl;

import com.hyy.kcbweekly.weekly.entity.VerificationCode;
import com.hyy.kcbweekly.weekly.mapper.VerificationCodeMapper;
import com.hyy.kcbweekly.weekly.service.IVerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
@Service
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper, VerificationCode> implements IVerificationCodeService {
    @Resource
    private VerificationCodeMapper verificationCodeMapper;

    @Override
    public VerificationCode findVerificationCodeByMobile(String mobile) {
        return verificationCodeMapper.findVerificationCodeByMobile(mobile);
    }
}
