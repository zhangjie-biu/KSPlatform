package com.zhangjie.controller;


import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.entity.User;
import com.zhangjie.enums.AppHttpCodeEnum;
import com.zhangjie.exception.SystemException;
import com.zhangjie.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @RequestMapping(path = "/login",method = {RequestMethod.POST})
    public ResponseResult<?> login(@RequestBody User user){
        //进行数据校验
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        //处理业务
        return ResponseResult.okResult(blogLoginService.login(user));
    }


    @RequestMapping(path = "/logout",method = {RequestMethod.POST})
    public ResponseResult<?> logout(){

        //处理业务
        blogLoginService.logout();

        return ResponseResult.okResult();
    }
}
