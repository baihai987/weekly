package com.hyy.kcbweekly.weekly.service.impl;

import com.hyy.kcbweekly.weekly.service.FileUploadService;
import com.hyy.kcbweekly.weekly.util.UploadImgUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    @Transactional
    public String uploadImg(MultipartFile file) {

        return UploadImgUtil.upLoadQuestionImg(file);
    }
}
