package com.zhangjie.service.impl;

import com.zhangjie.enums.AppHttpCodeEnum;
import com.zhangjie.exception.SystemException;
import com.zhangjie.service.UploadService;
import com.zhangjie.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    public OssUtil ossUtil;

    @Override
    public String uploadImg(MultipartFile img) {
        //判断文件类型
        String filename = img.getOriginalFilename();
        //如果判断通过上传文件到OSS
        if(!filename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }


        return ossUtil.uploadImg(img);
    }
}
