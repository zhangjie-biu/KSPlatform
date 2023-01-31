package com.zhangjie.exception;

import com.zhangjie.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException{
    private int code;

    private String msg;


    public int getCode(){
        return code;
    }

    public String getMessage(){
        return msg;
    }

    public SystemException(AppHttpCodeEnum appHttpCodeEnum){
        this.code = appHttpCodeEnum.getCode();
        this.msg = appHttpCodeEnum.getMsg();
    }


}
