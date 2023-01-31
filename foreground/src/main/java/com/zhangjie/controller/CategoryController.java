package com.zhangjie.controller;


import com.zhangjie.domain.ResponseResult;
import com.zhangjie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult<?> getCategoryList(){
        return ResponseResult.okResult(categoryService.getCategoryList());
    }
}
