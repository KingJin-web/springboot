package com.king.other.short_link.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-01-30 23:09
 */
@RestController
@RequestMapping("/file")
@Api(value = "文件上传接口", tags = "测试接口2")
public class FileController {

    private final String filePath = "F:\\A\\";

    @PostMapping("/upload")
    @ApiOperation(value = "上传单个文件", tags = "测试接口2")
    public String upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) {
        System.out.println(uploadFile);
        if (!uploadFile.isEmpty()) {
            return saveFile(uploadFile);
        }
        return "上传失败!";
    }

    @PostMapping("/uploads")
    @ApiOperation(value = "上传多个文件", tags = "测试接口2")
    public String upload(@RequestParam("files") MultipartFile[] uploadFiles, HttpServletRequest req) {
        if (uploadFiles.length > 0) {
            return saveFile(uploadFiles);
        }
        return "上传失败!";
    }

    public String saveFile(MultipartFile... multipartFiles) {
        StringBuilder sb = new StringBuilder();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                multipartFile.transferTo(new File(filePath ,multipartFile.getOriginalFilename()));

                sb.append(filePath).append(multipartFile.getOriginalFilename()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败!";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        File file = new File("F:\\A\\");
        //check file permissions for application user
        System.out.println("File is readable? " + file.canRead());
        System.out.println("File is writable? " + file.canWrite());
        System.out.println("File is executable? " + file.canExecute());
        //change file permissions for application user only
        file.setReadable(false);
        file.setWritable(false);
        file.setExecutable(false);
        //change file permissions for other users also
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, true);
    }


}
