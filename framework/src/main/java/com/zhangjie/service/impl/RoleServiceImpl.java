package com.zhangjie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjie.domain.entity.Role;
import com.zhangjie.mapper.RoleMapper;
import com.zhangjie.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-02-01 00:46:59
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        if(id == 1L){
            List<String> roleKeyList = new ArrayList<>();
            roleKeyList.add("admin");
            return roleKeyList;
        }
        return getBaseMapper().selectRoleKeyByuserId(id);
    }
}

