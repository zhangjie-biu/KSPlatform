package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.dto.AddArticleDto;
import com.zhangjie.domain.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    List<?> getHotArticleList();

    Object getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    Object getArticleDetail(Long id);

    void updateViewCount(Long id);

    void addArticle(AddArticleDto article);

    Object getArticleList(Integer pageNum, Integer pageSize);
}
