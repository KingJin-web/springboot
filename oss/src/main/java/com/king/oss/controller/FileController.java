package com.king.oss.controller;

import com.king.oss.service.QiniuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-01-30 23:09
 */
@RestController
@RequestMapping("/file")
public class FileController {

    //默认上传到的路径

    @Autowired
    QiniuServiceImpl qiniuService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) {
        if (!uploadFile.isEmpty()) {
            return qiniuService.saveFile(uploadFile);
        }
        return "上传失败!";
    }

    @PostMapping("/uploads")
    public List<String> upload(@RequestParam("files") MultipartFile[] uploadFiles, HttpServletRequest req) {
        if (!ObjectUtils.isEmpty(uploadFiles) && uploadFiles.length > 0) {
            return qiniuService.saveFile(uploadFiles);
        }
        return Collections.singletonList("上传失败!");
    }


}
