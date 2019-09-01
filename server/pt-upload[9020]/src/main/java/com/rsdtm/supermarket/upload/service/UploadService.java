package com.rsdtm.supermarket.upload.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.rsdtm.supermarket.upload.config.TxCosConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UploadService {

    @Autowired
    TxCosConfig txCosConfig;

    @Value("${tx.cos.bucket-name}")
    private  String bucketName;
    @Value("${tx.cos.prefix-url}")
    private String prefixUrl;
    @Value("${tx.cos.save-path}")
    private String savePath;


    @Autowired
    COSClient client;

    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg","image/gif");

    public String upload(MultipartFile file) {
        //1.验证文件
        //验证文件的类型
        String contentType = file.getContentType();
        if (!suffixes.contains(contentType)) {
            log.info("文件上传失败，类型不支持");
            return null;
        }
        //2.创建文件名 1级目录 2级目录
        String uuid = UUID.randomUUID().toString();
        int code = uuid.hashCode();
        int firstDir = code & 0xf;
        int secondDir = (code & 0xf0) >> 4;

        //3.拼接文件名
        //获取后缀名
        String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        String name = savePath+"/"+ firstDir + "/"+ secondDir + "/" + uuid + "." + extension;

        //上传文件


        try {
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 设置输入流长度
            objectMetadata.setContentLength(file.getSize());
            // 设置 Content type, 默认是 application/octet-stream
            objectMetadata.setContentType(file.getContentType());
            PutObjectResult putObjectResult = client.putObject(bucketName, name, fileInputStream, objectMetadata);
            String etag = putObjectResult.getETag();
            System.out.println(etag);
            String url = prefixUrl + name;
            log.info("文件上传成功："+url);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
