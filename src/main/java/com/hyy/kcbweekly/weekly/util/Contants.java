package com.hyy.kcbweekly.weekly.util;

import java.io.File;

public class Contants {
    static String ss = System.getProperty("user.dir");
    public static final String IMAGE_PATH = ss.substring(0, ss.lastIndexOf(File.separator)) + "/webapps/study/picture/";
    public static final String iINDEX_IMAGE_PATH = ss.substring(0, ss.lastIndexOf(File.separator)) + "/webapps/study/picture/imggif/";
    public static int PAGE_NUMBER = 10;
    public static final String SALT = "@%skjhg#";
}