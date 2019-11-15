package com.hyy.kcbweekly.weekly.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.hyy.kcbweekly.weekly.entity.ResultInfo;
import org.springframework.stereotype.Component;

@Component
public class AliyunMessageUtil {

    private DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
    private IAcsClient client = new DefaultAcsClient(profile);
    private CommonRequest request = new CommonRequest();
    private static final String accessKeyId = "LTAI4FgATErKgQoKA5V3kC2M";
    private static final String accessKeySecret = "GBvHWqeUopwXekUxoV3nxGG4yNYpdV";

    //发送消息返回 0 成功  -1失败
    public ResultInfo sendMessage(String phone, String code) {
        ResultInfo resultInfo = new ResultInfo();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "KingOfCobra");
        request.putQueryParameter("TemplateCode", "SMS_174809352");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse;
        try {
            CommonResponse response = client.getCommonResponse(request);
            sendSmsResponse = JSON.parseObject(response.getData(), SendSmsResponse.class);
            //发送成功
            if (sendSmsResponse.getCode().equals("OK")) {

                resultInfo.setMsg("短信发送成功");
                resultInfo.setCode(0);
            }
            //return -1;
        } catch (ServerException e) {
            e.printStackTrace();
            resultInfo.setMsg("短信发送失败");
            resultInfo.setCode(-1);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(-1);
            resultInfo.setMsg("短信发送失败");
        }

        return resultInfo;
    }

    /**
     * 生成随机数
     *
     * @param num 位数
     * @return
     */
    public  String createRandomNum(int num) {
        String randomNumStr = "";
        for (int i = 0; i < num; i++) {
            int randomNum = (int) (Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }

}
