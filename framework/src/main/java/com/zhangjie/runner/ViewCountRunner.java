package com.zhangjie.runner;

import com.zhangjie.constants.SystemConstants;
import com.zhangjie.domain.entity.Article;
import com.zhangjie.mapper.ArticleMapper;
import com.zhangjie.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class ViewCountRunner implements CommandLineRunner {


    @Autowired
    RedisCache redisCache;

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id 和 viewcount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        //存储到redis中
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_REDIS_CACHE,viewCountMap);

    }
}
