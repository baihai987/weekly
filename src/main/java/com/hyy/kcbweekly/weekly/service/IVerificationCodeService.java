package com.hyy.kcbweekly.weekly.service;

import com.hyy.kcbweekly.weekly.entity.VerificationCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
public interface IVerificationCodeService extends IService<VerificationCode> {
    VerificationCode findVerificationCodeByMobile(String mobile);
}
