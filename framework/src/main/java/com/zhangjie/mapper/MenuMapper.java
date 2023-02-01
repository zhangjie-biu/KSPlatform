package com.zhangjie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjie.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-31 17:32:32
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Menu> selectAllRouterMenu();
}

