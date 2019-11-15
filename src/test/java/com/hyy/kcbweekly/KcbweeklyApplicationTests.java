package com.hyy.kcbweekly;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyy.kcbweekly.weekly.controller.UserInfoController;
import com.hyy.kcbweekly.weekly.entity.TokenCode;
import com.hyy.kcbweekly.weekly.entity.UserInfo;
import com.hyy.kcbweekly.weekly.mapper.TokeCodeMapper;
import com.hyy.kcbweekly.weekly.service.IUserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KcbweeklyApplication.class)
public class KcbweeklyApplicationTests {
    @Autowired
    IUserInfoService userInfoService;

    //
    @Test
    public void contextLoads() {
    }

    @Test
    public void testUser1() {
        System.out.println("in");
        userInfoService.list(new QueryWrapper<UserInfo>()).forEach(v -> System.out.println(v.toString()));
    }

    @Resource
    private TokeCodeMapper tokeCodeMapper;

    @Test
    public void testReadYml() throws InterruptedException {
        TokenCode tokenCode = new TokenCode();
        tokenCode.setId(3);
        tokenCode.setMobile("18030451220");
        tokenCode.setToken("3d8e9d10-827b-4140-beba-5bdd2bcfd13d");
        tokenCode.setCreate_time(new Date());
        int update = tokeCodeMapper.update(tokenCode);
        System.out.println(update);
    }


}
