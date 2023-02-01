package com.zhangjie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjie.domain.entity.ArticleTag;
import com.zhangjie.mapper.ArticleTagMapper;
import com.zhangjie.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-01 06:32:01
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

