package com.zhangjie.controller;

import com.zhangjie.domain.ResponseResult;

import com.zhangjie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;



    @RequestMapping(value ="/hotArticleList",method = {RequestMethod.GET})
    public ResponseResult<?> hotArticleList(){
        //查询热门文章封装成ResponseResult返回

        return ResponseResult.okResult(articleService.getHotArticleList());

    }

    @RequestMapping(value = "/articleList",method = {RequestMethod.GET})
    public ResponseResult<?> ArticleList(Integer pageNum,Integer pageSize ,Long categoryId){
        //查询文章封装成ResponseResult返回

        return ResponseResult.okResult(articleService.getArticleList(pageNum,pageSize,categoryId));

    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.GET})
    public ResponseResult<?> articleDetail(@PathVariable("id") Long id){//获取restful形式的参数
        return ResponseResult.okResult(articleService.getArticleDetail(id));

    }


    @RequestMapping(value = "/updateViewCount/{id}",method = {RequestMethod.PUT})
    public ResponseResult<?> updateViewCount(@PathVariable("id") Long id){//获取restful形式的参数
        articleService.updateViewCount(id);
        return ResponseResult.okResult();

    }
}
