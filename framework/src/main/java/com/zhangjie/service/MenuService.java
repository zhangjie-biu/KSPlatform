package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.entity.Menu;
import com.zhangjie.domain.vo.RoutersVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 17:32:35
 */
public interface MenuService extends IService<Menu> {

    List<String>getPermsByUserId(Long id);

    RoutersVo getRouterMenuTreeByUserId();


}

