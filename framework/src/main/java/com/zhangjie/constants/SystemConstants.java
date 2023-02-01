package com.zhangjie.constants;

//常量类 用于定义代码中出现的常量，方便代码优化修改
public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 默认分页当前页
     */

    public static final int DEFULT_PAGE_CURRENT = 1;
    /**
     * 默认分页大小
     */
    public static final int DEFULT_PAGE_SIZE = 10;
    /**
     * 分类是正常状态
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    /**
     * 分类是不可用状态
     */
    public static final String CATEGORY_STATUS_DRAFT = "1";
    /**
     * 友链是审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * 友链审核未通过
     */
    public static final String LINK_STATUS_DRAFT = "1";
    /**
     * 友链未审核
     */
    public static final String LINK_STATUS_BEFORE = "2";

    /**
     * 根评论id
     */
    public static final String ROOT_COMMENT = "-1";
    /**
     * 友链评论
     */
    public static final String LINK_COMMENT = "1";
    /**
     * 文章评论
     */
    public static final String ARTICLE_COMMENT = "0";

    /**
     * 文章浏览缓存键
     */
    public static final String ARTICLE_VIEW_COUNT_REDIS_CACHE = "Article:viewCount";

    /**
     * 目录
     */
    public static final String MENU = "C";

    /**
     * 按钮
     */
    public static final String BUTTON = "F";
    /**
     *
     */
    public static final String STATUS_NORMAL = "0";
}

