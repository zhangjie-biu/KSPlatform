package com.zhangjie.service.impl;

import com.zhangjie.domain.entity.LoginUser;
import com.zhangjie.domain.entity.User;
import com.zhangjie.domain.vo.AdminUserInfoVo;
import com.zhangjie.domain.vo.UserInfoVo;
import com.zhangjie.service.AdminLoginService;
import com.zhangjie.service.MenuService;
import com.zhangjie.service.RoleService;
import com.zhangjie.utils.BeanCopyUtils;
import com.zhangjie.utils.JwtUtil;
import com.zhangjie.utils.RedisCache;
import com.zhangjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AdimnLoginServiceImpl implements AdminLoginService {

    @Autowired
    MenuService menuService;

    @Autowired
    RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;

    @Override
    public Map<String,String> login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        //把token和userinfo封装Vo返回
        return map;
    }

    @Override
    public void logout() {
        //获取token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        //删除redis缓存信息
        redisCache.deleteObject("bloglogin:"+userId);

    }

    @Override
    public AdminUserInfoVo getInfo() {
        //获取当前用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户查询权限
        List<String> perms = menuService.getPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //获取用户信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        return new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
    }
}
