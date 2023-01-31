package com.zhangjie.service.impl;

import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.entity.LoginUser;
import com.zhangjie.domain.entity.User;
import com.zhangjie.domain.vo.BlogUserLoginVo;
import com.zhangjie.domain.vo.UserInfoVo;
import com.zhangjie.service.BlogLoginService;
import com.zhangjie.utils.BeanCopyUtils;
import com.zhangjie.utils.JwtUtil;
import com.zhangjie.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;

    @Override
    public Object login(User user) {

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
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //把token和userinfo封装Vo返回
        return new BlogUserLoginVo(token,userInfoVo);
    }

    @Override
    public void logiout() {
        //获取token

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        //删除redis缓存信息
        redisCache.deleteObject("bloglogin:"+userId);
        return;
    }
}
