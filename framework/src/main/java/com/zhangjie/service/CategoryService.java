package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.entity.Category;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-01-14 22:56:35
 */
public interface CategoryService extends IService<Category> {

    List<?> getCategoryList();

    Object getCategoryList(Integer pageNum, Integer pageSize);

    Object listAllCategory();
}

