package com.hyy.kcbweekly.weekly.util;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class UploadImgUtil {
    public static String upLoadQuestionImg(MultipartFile file) {
        if (file == null || Objects.requireNonNull(file.getOriginalFilename()).trim().equals("")) {
            return "";
        }
        //判断是否为图片
        String filename = file.getOriginalFilename();
        String exname = filename.substring(filename.lastIndexOf(".") + 1);
        //图片的格式只能是：image  png  tif  jpg  jpeg  bmp
        if (!(exname.equalsIgnoreCase("jpg") || exname.equalsIgnoreCase("jpeg") || exname.equalsIgnoreCase("image") ||
                exname.equalsIgnoreCase("bmp") || exname.equalsIgnoreCase("png") || exname.equalsIgnoreCase("tif"))) {
            return "";
        }
        try {
            //给上传的文件重命名
            String originalFilename = file.getOriginalFilename();
            //文件的扩展名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            OSSClient ossClient = new OSSClient(OSSConstant.ENDPOINT, OSSConstant.ACCESSKEYID, OSSConstant.ACCESSKEYSECRET);
            ossClient.putObject(OSSConstant.BACKET_NAME, OSSConstant.QUESTIONIMAGEFOLDER + uuid + extension, file.getInputStream());
            //图片的过期时间为100年
            URL questionUrl = ossClient.generatePresignedUrl(OSSConstant.BACKET_NAME, OSSConstant.QUESTIONIMAGEFOLDER + uuid + extension, new Date(new Date().getTime() + Long.valueOf(OSSConstant.OSSExpireTime)));
            String questionUrl2 = questionUrl.toString();
            questionUrl2 = SplitImgUrlUtil.returnImageUrl(questionUrl2, OSSConstant.QUESTIONIMAGEURL, extension);
            return questionUrl2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
