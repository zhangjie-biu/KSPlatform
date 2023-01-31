package com.zhangjie.service;

import com.zhangjie.domain.entity.User;

public interface BlogLoginService {
    Object login(User user);

    void logiout();
}
