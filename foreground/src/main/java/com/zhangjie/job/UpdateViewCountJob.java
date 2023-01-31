package com.zhangjie.job;

import com.zhangjie.constants.SystemConstants;
import com.zhangjie.domain.entity.Article;
import com.zhangjie.service.ArticleService;
import com.zhangjie.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;


    @Scheduled(cron = "* 0/10 * * * ?")
    public void updateViewCount(){
        //要定时执行的代码
        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_REDIS_CACHE);

        List<Article> articles = cacheMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新数据库
        articleService.updateBatchById(articles);
    }
}
