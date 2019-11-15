package com.hyy.kcbweekly.weekly.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    //上传图片
    String uploadImg(MultipartFile file);


}
