package com.hyy.kcbweekly.weekly.quartz;

import com.hyy.kcbweekly.weekly.entity.TokenCode;
import com.hyy.kcbweekly.weekly.mapper.TokeCodeMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@Configurable
@EnableScheduling
public class RefreshTokenQuartz {
    @Resource
    private TokeCodeMapper tokeCodeMapper;

    //30分钟执行一次
    @Scheduled(cron = "0 0/1  * * * ?")
    //查询token
    public void reportCurrent() {
        System.out.println("定时器执行了");
        List<TokenCode> list = tokeCodeMapper.findAllToken();
        if (list != null && list.size() > 0) {
            for (TokenCode tokenCode : list) {
                long time = tokenCode.getCreate_time().getTime();
                long time1 = new Date().getTime();
                long differ = time1 - time;
                if (differ > 30 * 60 * 1000L) {
                    int delete = tokeCodeMapper.delete(tokenCode);
                    System.out.println("删除成功" + tokenCode);
                }
            }
        }
    }
}
