package com.hyy.kcbweekly.weekly.mapper.provider;


public class TokenCodeProvider {

    public TokenCodeProvider() {
    }

    public String findToken(String token) {
        String sql = "SELECT kt.id,kt.token,kt.mobile,kt.create_time FROM kcb_token kt WHERE 1=1 ";
        sql += " and kt.token = '" + token + "'";
        return sql;
    }
}
