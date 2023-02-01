package com.zhangjie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjie.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-01 00:46:56
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByuserId(Long id);
}

