package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-02-01 00:46:59
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}

