package com.zhangjie.handler.exception;


//此处用来处理业务层产生的异常

import com.zhangjie.domain.ResponseResult;
import com.zhangjie.enums.AppHttpCodeEnum;
import com.zhangjie.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //@RestControllerAdvice和@ControllerAdvice与ResponseBody 和@RestController 与@Controller与ResponseBody一样
@Slf4j //打印日志
public class GlobalExceptionHandler {


    @ExceptionHandler(SystemException.class) //类似于RequestMapping
    public ResponseResult sysExceptionHandler(SystemException e){
        //打印异常信息
        log.error("异常信息"+e.getMessage(),e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.error("异常信息"+e.getMessage(),e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }

}
