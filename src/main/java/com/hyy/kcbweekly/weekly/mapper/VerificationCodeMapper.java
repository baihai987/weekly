package com.hyy.kcbweekly.weekly.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyy.kcbweekly.weekly.entity.VerificationCode;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
@Mapper
public interface VerificationCodeMapper extends BaseMapper<VerificationCode> {
    VerificationCode findVerificationCodeByMobile(String mobile);

    VerificationCode findMobileCode(String mobile, String code);
}
