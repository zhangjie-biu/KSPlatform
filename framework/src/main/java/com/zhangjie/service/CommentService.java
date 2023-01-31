package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-01-19 01:47:19
 */
public interface CommentService extends IService<Comment> {

    Object getCommentList(String commentType, Long articleId, Integer pageName, Integer pageSize);

    void addComment(Comment comment);

}

