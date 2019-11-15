package com.hyy.kcbweekly.weekly.service;

import com.hyy.kcbweekly.weekly.entity.ResultInfo;
import com.hyy.kcbweekly.weekly.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyy.kcbweekly.weekly.entity.VerificationCode;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
public interface IUserInfoService extends IService<UserInfo> {

    Map login(String mobile, String code);

}
