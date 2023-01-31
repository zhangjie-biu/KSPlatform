package com.zhangjie.controller;


import com.zhangjie.domain.ResponseResult;
import com.zhangjie.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @RequestMapping("/getAllLink")
    public ResponseResult<?> getAllLink(){

        return ResponseResult.okResult(linkService.getAllLink());
    }
}
