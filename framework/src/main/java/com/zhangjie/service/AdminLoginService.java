package com.zhangjie.service;

import com.zhangjie.domain.entity.User;
import com.zhangjie.domain.vo.AdminUserInfoVo;

import java.util.Map;

public interface AdminLoginService {
    Map<String,String> login(User user);


    void logout();

    AdminUserInfoVo getInfo();
}
