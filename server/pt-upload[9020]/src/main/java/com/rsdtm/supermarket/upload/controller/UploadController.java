package com.rsdtm.supermarket.upload.controller;

import com.rsdtm.supermarket.upload.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@CrossOrigin("http://localhost:9528")
public class UploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping
    public ResponseEntity<String> upload(MultipartFile file){
        String url = uploadService.upload(file);
        if (StringUtils.isBlank(url)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(url);
    }

    @GetMapping
    public ResponseEntity<Void> test(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
