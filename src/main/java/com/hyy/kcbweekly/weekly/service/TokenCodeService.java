package com.hyy.kcbweekly.weekly.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyy.kcbweekly.weekly.entity.TokenCode;

public interface TokenCodeService {
    TokenCode findToken(String token);

    Integer insert(TokenCode tokenCode);
}
