package com.hyy.kcbweekly.weekly.util;

import com.hyy.kcbweekly.weekly.entity.TokenCode;
import com.hyy.kcbweekly.weekly.entity.VerificationCode;
import com.hyy.kcbweekly.weekly.service.TokenCodeService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public  class CheckUtil {
    @Resource

    @Insert("insert into kcb_token(id,mobile,token,create_time) values(#{id},#{mobile},#{token},#{create_time}) ")
    void insert(TokenCode tokenCode){

    };

    //校验电话号码和验证码
    public  static Map checkMobileCode(VerificationCode verificationCode) {
        HashMap<String, String> map = new HashMap<>();
        //没有查询到验证码

        if (verificationCode == null) {
            //-1没有此验证码或者已经过期
            map.put("code", "-1");
            map.put("data", "请重新发送验证码");
            return map;
        }
        //0返回token
        //long createTime = verificationCodeByMobile.getCreateTime().toEpochSecond(ZoneOffset.of("+8"));
        long creatTime = verificationCode.getCreateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long time = new Date().getTime();
        long differ = time - creatTime;
        if (differ > 5 * 1000 * 60L) {
            //重新发送验证码
            map.put("code", "-1");
            map.put("data", "请重新发送验证码");
            return map;
        }
        String token = UUID.randomUUID().toString();
        map.put("code", "0");
        map.put("data", token);
        System.out.println(map);

        return map;
    }
}
