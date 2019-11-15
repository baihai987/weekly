package com.hyy.kcbweekly.weekly.util;

public class OSSConstant {
    public static final String ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";
    public static final String ACCESSKEYID = "";
    public static final String ACCESSKEYSECRET = "";

    //图片过期时间为100年
    public static final Long OSSExpireTime = 3153600000000L;
    //图片的仓库
    public static final String BACKET_NAME = "";
    //用户的问题图片路径
    public static final String QUESTIONIMAGEFOLDER = "MemberQuestionImage/";
    //用于切割用户问题图片的路径
    public static final String QUESTIONIMAGEURL = BACKET_NAME+".oss-cn-hangzhou.aliyuncs.com/MemberQuestionImage/";
    //广告图片的路径
    public static final String ADIMGFOLDER = "AdImage/";
    //用于切割广告图片的路径
    public static final String ADIMAGEURL = BACKET_NAME+".oss-cn-hangzhou.aliyuncs.com/AdImage/";
    //徽章图片的路径
    public static final String MEDALIMGFOLDER = "MedalImg/";
    //用于切割徽章图片的路径
    public static final String MEDALIMAGEURL = BACKET_NAME+".oss-cn-hangzhou.aliyuncs.com/MedalImg/";
}
