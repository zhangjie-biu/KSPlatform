package com.zhangjie.controller;

import com.zhangjie.domain.ResponseResult;

import com.zhangjie.domain.entity.User;
import com.zhangjie.domain.vo.AdminUserInfoVo;
import com.zhangjie.domain.vo.RoutersVo;
import com.zhangjie.enums.AppHttpCodeEnum;
import com.zhangjie.exception.SystemException;
import com.zhangjie.service.AdminLoginService;
import com.zhangjie.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;



@RestController
public class LoginController {
    @Autowired
    private AdminLoginService loginService;

    @Autowired
    MenuService menuService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return ResponseResult.okResult(loginService.login(user));
    }

    @RequestMapping(path = "/user/logout",method = {RequestMethod.POST})
    public ResponseResult<?> logout(){

        //处理业务
        loginService.logout();

        return ResponseResult.okResult();
    }

    @RequestMapping(path = "/user/getInfo",method = {RequestMethod.GET})
    public ResponseResult<AdminUserInfoVo> getInfo(){
        return ResponseResult.okResult(loginService.getInfo());
    }



    @RequestMapping(path = "/getRouters",method = {RequestMethod.GET})
    public ResponseResult<RoutersVo> getRouters(){

        return ResponseResult.okResult(menuService.getRouterMenuTreeByUserId());
    }



}