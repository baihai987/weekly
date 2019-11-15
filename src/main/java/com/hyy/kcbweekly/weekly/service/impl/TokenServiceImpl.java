package com.hyy.kcbweekly.weekly.service.impl;

import com.hyy.kcbweekly.weekly.entity.TokenCode;
import com.hyy.kcbweekly.weekly.mapper.TokeCodeMapper;
import com.hyy.kcbweekly.weekly.service.TokenCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TokenServiceImpl implements TokenCodeService {

    @Resource
    private TokeCodeMapper tokeCodeMapper;
    @Override
    public TokenCode findToken(String token) {
        return null;
    }

    @Override
    public Integer insert(TokenCode tokenCode) {

        return tokeCodeMapper.insert(tokenCode);
    }
}
