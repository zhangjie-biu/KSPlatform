package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-01-19 02:43:09
 */
public interface UserService extends IService<User> {

    Object getUserInfo();

    void updateUserInfo(User user);

    void register(User user);
}

