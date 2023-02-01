package com.zhangjie.controller;


import com.zhangjie.domain.ResponseResult;
import com.zhangjie.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class uploadController {

    @Autowired
    private UploadService uploadService;


    @RequestMapping(path = "/upload",method = {RequestMethod.POST})
    public ResponseResult<?> uploadImg(MultipartFile img){
        return ResponseResult.okResult(uploadService.uploadImg(img));
    }
}
