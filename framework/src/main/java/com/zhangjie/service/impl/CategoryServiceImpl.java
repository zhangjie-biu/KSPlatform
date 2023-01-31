package com.zhangjie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjie.constants.SystemConstants;
import com.zhangjie.domain.entity.Article;
import com.zhangjie.domain.entity.Category;
import com.zhangjie.domain.vo.CategoryVo;
import com.zhangjie.mapper.CategoryMapper;
import com.zhangjie.service.ArticleService;
import com.zhangjie.service.CategoryService;
import com.zhangjie.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-01-14 22:56:35
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    ArticleService articleService;

    @Override
    public List<?> getCategoryList() {
        // 查询文章表 状态为已发布的文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(articleLambdaQueryWrapper);

        // 获取文章的分类ID 并且去重
        Set<Long> categoryIds = articles.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        List<Category> categories1 = categories.stream().filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装成VO

        return BeanCopyUtils.copyBeanList(categories1, CategoryVo.class);
    }
}

