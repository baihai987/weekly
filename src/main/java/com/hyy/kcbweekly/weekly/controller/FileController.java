package com.hyy.kcbweekly.weekly.controller;


import com.hyy.kcbweekly.weekly.anno.Token;
import com.hyy.kcbweekly.weekly.entity.ResultInfo;
import com.hyy.kcbweekly.weekly.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/file")
public class FileController {
    @Token
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
//
//    //文件上传相关代码
//    @RequestMapping(value = "upload")
//    @ResponseBody
//    public String upload(@RequestParam("test") MultipartFile file) {
//        if (file.isEmpty()) {
//            return "文件为空";
//        }
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//        logger.info("上传的文件名为：" + fileName);
//        // 获取文件的后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        logger.info("上传的后缀名为：" + suffixName);
//
//        // 文件上传后的路径
//        String filePath = "E://test//";
//        // 解决中文问题，liunx下中文路径，图片显示问题
//        // fileName = UUID.randomUUID() + suffixName;
//        File dest = new File(filePath + fileName);
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//            return "上传成功";
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "上传失败";
//    }
//
//    //文件下载相关代码
//    @RequestMapping("/download")
//    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "FileUploadTests.java";
//        if (fileName != null) {
//            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
//            String realPath = request.getServletContext().getRealPath(
//                    "//WEB-INF//");
//            File file = new File(realPath, fileName);
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition",
//                        "attachment;fileName=" + fileName);// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    System.out.println("success");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    //多文件上传
//    // 图片上传返回图片名字
//    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultInfo handleFileUpload(HttpServletRequest request, HttpServletResponse response) {
//        ResultInfo resultInfo = new ResultInfo();
//        String resultImg = "";
//        // 图片地址
////        List list = new ArrayList();
////        JSONArray jsonImgs = new JSONArray();
//
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
//                .getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        //       String pathname = "F:/newimagetest/";
//
////        if (file.isEmpty()) {
////            return "文件为空";
////        }
//        // 获取文件名
//        String fileName = "";
//        logger.info("上传的文件名为：" + fileName);
//        // 获取文件的后缀名
//        String suffixName = "";
//        logger.info("上传的后缀名为：" + suffixName);
//        // 文件上传后的路径
//        String filePath = "F:/newpackage/good/pring/test";
//
//        File dest = new File(filePath + fileName);
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            fileName = file.getOriginalFilename();
//            suffixName = fileName.substring(fileName.lastIndexOf("."));
//            fileName = UUID.randomUUID() + suffixName;
//            logger.info("=================> " + fileName);
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(filePath + fileName)));
//                    stream.write(bytes);
//                    stream.close();
//                    resultImg = fileName;
////                    JSONObject jo = new JSONObject();
////                    jo.put("img" + i, fileName);
////                    //jo.put("time", people.getAge());
////                    jsonImgs.add(jo);
////                    list.add(fileName);
//
//                } catch (Exception e) {
//                    stream = null;
//                    resultInfo.setMsg("You failed to upload " + i + " => "
//                            + e.getMessage());
//                    resultInfo.setCode(-1);
//                    return resultInfo;
//                }
//            } else {
//                resultInfo.setMsg("You failed to upload " + i
//                        + " because the file was empty.");
//                resultInfo.setCode(-2);
//                return resultInfo;
//            }
//        }
//
//        resultInfo.setCode(response.getStatus());
//        resultInfo.setMsg("图片上传成功");
//
////        String inventoryResultStr = JSON.toJSON(list).toString();
////        String str = JSON.toJSONString(list);
//
////        JSONArray jsonArray = new JSONArray();
////        String jsonList = jsonArray.toJSONString();
//        return resultInfo.add(resultImg);
//    }

    @Resource
    private FileUploadService fileUploadService;

    //阿里云上传图片
    @Token
    @PostMapping("/imgUpload")
    public ResultInfo uploadImg(@RequestParam MultipartFile file, HttpServletResponse response) {
        ResultInfo resultInfo = new ResultInfo();
        String imgString = fileUploadService.uploadImg(file);
        if (imgString.equals("")) {
            resultInfo.setCode(-1);
            resultInfo.setMsg("图片上传失败");
            return resultInfo;
        }
        resultInfo.setCode(-1);
        resultInfo.setData(imgString);
        return resultInfo;
    }
}
