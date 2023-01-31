package com.zhangjie.controller;


import com.zhangjie.annotation.SystemLog;
import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.entity.User;
import com.zhangjie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/userInfo",method = {RequestMethod.GET})
    public ResponseResult<?> userInfo(){
        return ResponseResult.okResult(userService.getUserInfo());
    }


    @RequestMapping(path = "/userInfo",method = {RequestMethod.PUT})
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult<?> updateUserInfo(@RequestBody User user){
        userService.updateUserInfo(user);
        return ResponseResult.okResult();
    }


    @RequestMapping(path = "/register",method = {RequestMethod.POST})
    public ResponseResult<?> register(@RequestBody User user){
        userService.register(user);
        return ResponseResult.okResult();
    }



}
