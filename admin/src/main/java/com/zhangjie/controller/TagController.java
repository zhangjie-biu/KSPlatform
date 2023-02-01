package com.zhangjie.controller;


import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.entity.Tag;
import com.zhangjie.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping (path = "/list",method = {RequestMethod.GET})
    public ResponseResult list(Integer pageNum,Integer pageSize){
        return ResponseResult.okResult(tagService.getlist(pageNum,pageSize));
    }

    @RequestMapping (method = {RequestMethod.POST})
    public ResponseResult addTag(@RequestBody Tag tag){

        tagService.addTag(tag);

        return ResponseResult.okResult();
    }

    @RequestMapping (path = "/{ids}",method = {RequestMethod.DELETE})
    public ResponseResult deleteTag(@PathVariable String ids){
        String[] split = ids.split(",");
        List<Long> conIds = new ArrayList<>();
        for(String s:split){
            conIds.add(Long.valueOf(s));
        }
        tagService.deleteTag(conIds);
        return ResponseResult.okResult();
    }

    @RequestMapping (path = "/{id}",method = {RequestMethod.GET})
    public ResponseResult getTag(@PathVariable Long id){


        return ResponseResult.okResult(tagService.getTag(id));
    }

    @RequestMapping (method = {RequestMethod.PUT})
    public ResponseResult updateTag(@RequestBody Tag tag){

        tagService.updateTag(tag);

        return ResponseResult.okResult();
    }

    @RequestMapping (path = "/listAllTag",method = {RequestMethod.GET})
    public ResponseResult getAlllist(){
        return ResponseResult.okResult(tagService.getAlllist());
    }

}