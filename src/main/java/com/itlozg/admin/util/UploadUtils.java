package com.itlozg.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/11/22 0022.
 */
public class UploadUtils {

    private Logger logger = LoggerFactory.getLogger(UploadUtils.class);
    // 文件保存目录相对路径
    // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    //测试环境
//    private String basePath = "";
    //正式环境
    private String basePath = "";

    // 文件保存目录路径
    private String savePath;
    // 文件保存目录url
    private String saveUrl;
    private Random rand = new Random();

    /**
     * 文件上传
     *
     * @param file    上传文件
     * @param request 上传请求
     * @param dire    文件保存目录
     * @return
     */
    public Map<String, String> upload(MultipartFile file, HttpServletRequest request, String dire) {
        Map<String, String> infos = new HashMap<String, String>();
        int random = rand.nextInt(100) + 1;
        try {
            if (dire != null && !dire.equals("")) {
                basePath += dire + "/";
            }
            //测试环境
//            savePath = request.getSession().getServletContext().getRealPath("/") + basePath;
            //正式环境
            savePath = basePath;//文件上传路径
            String fileName = file.getOriginalFilename();
            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String saveName = DateUtils.getDate("yyyyMMddHHmmss") + "_" + random + "." + prefix;//文件保存名称
            File targetFile = new File(savePath, saveName);
            saveUrl = basePath + saveName;
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            infos.put("status", "ok");
            infos.put("saveName", saveName);
            //            测试环境
//            infos.put("savePath", saveUrl);
//            infos.put("saveUrl", saveUrl);
//            正式环境
            infos.put("savePath", "/static/uploadFile/" + dire + "/" + saveName);
            infos.put("saveUrl", "/static/uploadFile/" + dire + "/" + saveName);
            infos.put("prefix", prefix);
        } catch (Exception e) {
            e.printStackTrace();
            infos.put("status", "error");
            logger.error("[文件上传]：", e);
        }
        return infos;
    }

    //删除文件
    public Map<String, String> delete(HttpServletRequest request, String dire) {
        Map<String, String> infos = new HashMap<String, String>();
        try {
//            String url = request.getSession().getServletContext().getRealPath("") + dire;//文件上传路径
//            File file = new File(url);
//            file.delete();
//            infos.put("status", "ok");
        } catch (Exception e) {
            e.printStackTrace();
            infos.put("status", "error");
            logger.error("[文件删除：", e);
        }
        return infos;
    }

    /**
     * 报告修改后保存
     *
     * @param file
     * @param filePath
     * @return
     */
    public Map<String, String> uploadReport(MultipartFile file, String filePath) {
        Map<String, String> infos = new HashMap<String, String>();
        try {
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            infos.put("status", "ok");
        } catch (Exception e) {
            infos.put("status", "error");
            logger.error("[文件上传]：", e);
        }
        return infos;
    }
}
