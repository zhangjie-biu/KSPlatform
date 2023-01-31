package com.zhangjie.controller;


import com.zhangjie.constants.SystemConstants;
import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.entity.Comment;
import com.zhangjie.enums.AppHttpCodeEnum;
import com.zhangjie.exception.SystemException;
import com.zhangjie.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {

    @Autowired
    CommentService commentService;


    @RequestMapping(path = "/commentList",method = {RequestMethod.GET})
    public ResponseResult<?> commentList(Long articleId,Integer pageNum,Integer pageSize){

        return ResponseResult.okResult(commentService.getCommentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize));
    }


    @RequestMapping(method = {RequestMethod.POST})
    public ResponseResult<?> comment(@RequestBody Comment comment){

        //pl内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONMMENT_NOTNULL);
        }
        //将评论存入数据库
        commentService.addComment(comment);
        return ResponseResult.okResult();
    }

    @RequestMapping(path = "/linkCommentList",method = {RequestMethod.GET})
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult<?> linkCommentList(Integer pageNum,Integer pageSize){

        return ResponseResult.okResult(commentService.getCommentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize));
    }

}
