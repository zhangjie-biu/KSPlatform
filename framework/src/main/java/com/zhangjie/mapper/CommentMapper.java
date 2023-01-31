package com.zhangjie.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjie.domain.entity.Comment;

import java.util.List;


/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-19 01:47:17
 */
public interface CommentMapper extends BaseMapper<Comment> {

}

