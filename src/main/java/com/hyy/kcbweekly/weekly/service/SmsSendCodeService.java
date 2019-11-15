package com.hyy.kcbweekly.weekly.service;

public interface SmsSendCodeService {

    //发送验证码
    Integer SmsSendCode(String mobile);
}
