package com.zhangjie.service.permission;


import com.zhangjie.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ps")
public class PermissionService {
    /**
     * 判断当前用户是否具有permission
     * @parms permission 要判断的权限
     * @return
     */

    public boolean hasPermission(String permission){
        //如果是超级管理员直接返回true
        if(SecurityUtils.isAdmin()){
            return true;
        }
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
