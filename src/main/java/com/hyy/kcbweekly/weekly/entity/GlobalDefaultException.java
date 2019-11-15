package com.hyy.kcbweekly.weekly.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by JinZhicheng on 2018/11/22 21:55
 * <p>
 * 全局异常捕获
 */
@ControllerAdvice
public class GlobalDefaultException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultException.class);

//    @ResponseBody   // 如果返回String或json要加此注解如果返回界面就不加
//    @ExceptionHandler(OpenApiException.class)
//    public ResultInfo openApiException(OpenApiException e) {
//        return e.getResultType();
//    }
//
//    @ResponseBody   // 如果返回String或json要加此注解如果返回界面就不加
//    @ExceptionHandler(AccountException.class)
//    public ResultInfo accountException(AccountException e) {
//        return ResultInfo.buildFailResult(e.getResultType());
//    }
//
//    @ExceptionHandler(BusinessException.class)
//    @ResponseBody   // 如果返回String或json要加此注解如果返回界面就不加
//    public ResultInfo businessException(HttpServletRequest req, Exception e) {
//        // 返回String
//        logger.info("异常信息：", e);
//        return ResultInfo.failWithMsg(10001, e.getMessage());
//    }
//
//    @ExceptionHandler(OrderException.class)
//    @ResponseBody   // 如果返回String或json要加此注解如果返回界面就不加
//    public ResultInfo orderException(HttpServletRequest req, Exception e) {
//        // 返回String
//        logger.info("异常信息：", e);
//        return ResultInfo.failWithMsg(10001, e.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    @ResponseBody   // 如果返回String或json要加此注解如果返回界面就不加
    public ResultInfo defaultExceptionHandler(HttpServletRequest req, Exception e) {
        // 返回String
        logger.info("异常信息：", e);
        return ResultInfo.fail();
    }
}
