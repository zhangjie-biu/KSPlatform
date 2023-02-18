package com.zhangjie.controller;

import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.dto.AddArticleDto;
import com.zhangjie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;



    @RequestMapping(value ="/content/article",method = {RequestMethod.POST})
    public ResponseResult<?> addArticle(@RequestBody AddArticleDto article){
        //查询热门文章封装成ResponseResult返回

        articleService.addArticle(article);
        return ResponseResult.okResult();

    }

    @RequestMapping(value = "/content/article/list",method = {RequestMethod.GET})
    public ResponseResult<?> ArticleList(Integer pageNum,Integer pageSize ,Long categoryId){
        //查询文章封装成ResponseResult返回

        return ResponseResult.okResult(articleService.getArticleList(pageNum,pageSize));

    }

    @RequestMapping(value = "/content/article/{id}",method = {RequestMethod.GET})
    public ResponseResult<?> getArticle(@PathVariable Long id){
        //查询文章封装成ResponseResult返回

        return ResponseResult.okResult(articleService.getArticle(id));

    }


    @RequestMapping(value ="/content/article",method = {RequestMethod.PUT})
    public ResponseResult<?> updateArticle(@RequestBody AddArticleDto article){
        //查询热门文章封装成ResponseResult返回

        articleService.updateArticle(article);
        return ResponseResult.okResult();

    }


}
