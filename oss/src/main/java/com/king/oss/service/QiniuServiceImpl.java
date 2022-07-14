package com.king.oss.service;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: 12613
 * @project: mooc
 * @pcakage: com.king.mooc.service.impl.QiniuServiceImpl
 * @date: 2022年05月02日 00:38
 * @description:
 */
@Service
public class QiniuServiceImpl {
    @Value(value = "${Qiniu.AccessKey}")
    private String accessKey;
    @Value(value = "${Qiniu.SecretKey}")
    private String secretKey;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //测试文件上传
    public void testUpload() {
        //构造一个带指定 Region 对象的配置类，指定存储区域，和存储空间选择的区域一致
        Configuration cfg = new Configuration(Region.huanan());
        //其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //生成上传凭证，然后准备上传
        String bucket = "hnking";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID() + ".png";
        FileInputStream fileInputStream = null;
        try {

            String filePath = "C:\\Users\\12613\\Pictures\\20201029195525.png";
            fileInputStream = new FileInputStream(new File(filePath));
            //得到本地文件的字节数组
            byte[] bytes = IOUtils.toByteArray(fileInputStream);
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            //认证
            Auth auth = Auth.create(accessKey, secretKey);
            //认证通过后得到token（令牌）
            String upToken = auth.uploadToken(bucket);
            try {
                //上传文件,参数：字节数组，key，token令牌
                //key: 建议我们自已生成一个不重复的名称
                Response response = uploadManager.put(bytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = JSON.parseObject(response.bodyString(),
                        DefaultPutRet.class);
                logger.info("上传成功{}", JSON.toJSONString(putRet));
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    logger.error("上传失败{}", ex2.getMessage());
                }
            }
        } catch (IOException ex) {
            logger.error("上传文件失败", ex);
            //ignore
        }

    }

    public String saveFile(MultipartFile uploadFile) {
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        String bucket = "hnking";
        String fileName = uploadFile.getOriginalFilename();
        String key = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadFile.getBytes(), key, upToken);
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return "http://rb3lparfv.hn-bkt.clouddn.com/" + putRet.key;
        } catch (QiniuException e) {
            logger.error("上传文件失败", e);
            logger.error(JSON.toJSONString(e.response));
        } catch (IOException e) {
            logger.error("上传文件失败", e);
        }
        return null;
    }

    public List<String> saveFile(MultipartFile[] uploadFiles) {
        List<String> list = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            list.add(saveFile(uploadFile));
        }
        return list;
    }
}