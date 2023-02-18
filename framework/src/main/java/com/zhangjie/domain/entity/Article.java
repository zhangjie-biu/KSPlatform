package com.zhangjie.domain.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文章表(Article)实体类
 *
 * @author makejava
 * @since 2023-01-10 11:32:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Article  {
    private static final long serialVersionUID = 214354734406052518L;
    
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 所属分类id
     */
    private Long categoryId;
    /**
     * 缩略图
     */
    /**\
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryName;
    private String thumbnail;
    /**
     * 是否置顶（0否，1是）
     */
    private String isTop;
    /**
     * 状态（0已发布，1草稿）
     */
    private String status;
    /**
     * 访问量
     */
    private Long viewCount;
    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;
    /**
     * 标签
     */
    @TableField(exist = false)
    private List<Long> tags;

    @TableField(fill= FieldFill.INSERT)
    private Long createBy;
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;
    @TableField(fill= FieldFill.UPDATE)
    private Long updateBy;
    @TableField(fill= FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;


    public Article(Long id, Long viewCount) {
        this.id = id;
        this.viewCount=viewCount;

    }
}

