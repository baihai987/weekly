package com.hyy.kcbweekly.weekly.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyy.kcbweekly.weekly.anno.NoLogin;
import com.hyy.kcbweekly.weekly.entity.ResultInfo;
import com.hyy.kcbweekly.weekly.entity.UserInfo;
import com.hyy.kcbweekly.weekly.service.IUserInfoService;
import com.hyy.kcbweekly.weekly.service.SmsSendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cenbihua
 * @since 2019-11-13
 */
@RestController
@RequestMapping("//user-info")
public class UserInfoController {
    @Autowired
    IUserInfoService iUserInfoService;

    @RequestMapping("/users")
    public List<UserInfo> test() {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        List<UserInfo> userInfos = iUserInfoService.list(wrapper);
        return userInfos;
    }

    @NoLogin(required = true)
    @PostMapping("/login")
    public ResultInfo login(@RequestParam String mobile_number, @RequestParam String code) {
        ResultInfo resultInfo = new ResultInfo();
        System.out.println(mobile_number + "         " + code);
        Map map = iUserInfoService.login(mobile_number, code);

        if (map.get("code").equals("-1")) {
            resultInfo.setCode(-1);
            resultInfo.setData(map.get("data"));
            return resultInfo;
        }
        resultInfo.setCode(0);
        resultInfo.setData(map.get("data"));
        return resultInfo;
    }

    @Resource
    private SmsSendCodeService smsSendCodeService;

    @NoLogin(required = true)
    @ResponseBody
    @PostMapping("/sendCode")
    public ResultInfo sendCode(@RequestParam String mobile_number) {
        System.out.println("接受到的电话号码" + mobile_number);
        Integer integer = smsSendCodeService.SmsSendCode(mobile_number);
        if (integer == 0) {
            return ResultInfo.success();
        } else if (integer == -2) {
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setCode(10002);
            resultInfo.setMsg("操作频繁，请稍后再试");
            return resultInfo;
        }
        return ResultInfo.fail();
    }

}
