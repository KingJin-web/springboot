package com.king.other.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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

    //默认上传到的路径
    private final String filePath = "F:\\A\\";

    @PostMapping("/upload")
    @ApiOperation(value = "上传单个文件", tags = "测试接口2")
    public String upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) {
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

    /**
     *
     * @param multipartFiles
     * @return 返回保存的路径
     */
    public String saveFile(MultipartFile... multipartFiles) {
        StringBuilder sb = new StringBuilder();
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                multipartFile.transferTo(new File(filePath, multipartFile.getOriginalFilename()));

                sb.append(filePath).append(multipartFile.getOriginalFilename()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败!";
        }
        return sb.toString();
    }

}
