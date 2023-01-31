package com.zhangjie.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjie.constants.SystemConstants;
import com.zhangjie.domain.entity.Comment;
import com.zhangjie.domain.vo.CommentVo;
import com.zhangjie.domain.vo.PageVo;
import com.zhangjie.mapper.CommentMapper;
import com.zhangjie.service.CommentService;
import com.zhangjie.service.UserService;
import com.zhangjie.utils.BeanCopyUtils;
import com.zhangjie.utils.SecurityUtils;
import io.lettuce.core.protocol.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-01-19 01:47:19
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    UserService userService;

    @Override
    public Object getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //分页查询文章的根评论
        LambdaQueryWrapper<Comment> lambdaQueryWrapper =new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        lambdaQueryWrapper.eq(SystemConstants.LINK_COMMENT.equals(commentType),Comment::getType,commentType);
        lambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT);
        //分页查询
        Page<Comment> page = new Page(pageNum,pageSize);
        page(page, lambdaQueryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        //查询所有根评论对应的子评论集合，并赋值给相应属性
        commentVoList
                .stream()
                .map(CommentVo->CommentVo.setChildren(getChildren(CommentVo.getId())))
                .collect(Collectors.toList());


        return new PageVo(commentVoList,page.getTotal());
    }

    @Override
    public void addComment(Comment comment) {
        Long userId = SecurityUtils.getUserId();
        comment.setCreateBy(userId);

        //存入数据库
        save(comment);
        return;

    }



    //转换Comment为CommentVo
    private List<CommentVo> toCommentVoList(List<Comment> comments){
        //遍历Vo集合
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(comments,CommentVo.class);
        //通过CreateBy查询用户昵称并赋值,通过toCommentUserId查询用户昵称并赋值
        //通过toCommentUserId查询用户昵称并赋值,此处toCommentUserId可能为-1，即为根评论，没有被评论人
        commentVoList.stream()
                .map(CommentVo->CommentVo.setUsername(userService.getById(CommentVo.getCreateBy()).getNickName()))
                .collect(Collectors.toList())
        //TODO 此处可能实现还有问题，没有测试到非根评论
                .stream()
                .filter(CommentVo->CommentVo.getToCommentUserId()!=-1)
                .forEach(CommentVo->CommentVo.setToCommentUserName(userService.getById(CommentVo.getToCommentUserId()).getNickName()));



        return commentVoList;
    }


    /**
     * 根据根评论的id查询所对应子评论集合
     * @param rootId 根评论id
     * @return
     */
    public List<CommentVo> getChildren(Long rootId){
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //对子评论进行排序
        lambdaQueryWrapper.orderByAsc(Comment::getCreateTime);
        //查询rootid为父id的评论列表
        lambdaQueryWrapper.eq(Comment::getRootId,rootId);
        List<Comment> commentList = list(lambdaQueryWrapper);
        return toCommentVoList(commentList);
    }
}

