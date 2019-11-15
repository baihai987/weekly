package com.hyy.kcbweekly.weekly.util;

public class SplitImgUrlUtil {
    //返回一个切割好的图片url
    public static String returnImageUrl(String str,String imageUrl,String extension){
        //str = "http://yuehoo.oss-cn-hangzhou.aliyuncs.com/BookCover/7a89bfbe379d42d39e02cd03f409ba87.jpg";
        String substring = str.substring(str.lastIndexOf(imageUrl), str.lastIndexOf("."));
        return  "https://"+substring+extension;
    }

}
