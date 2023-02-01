package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.entity.Tag;
import com.zhangjie.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 15:48:40
 */
public interface TagService extends IService<Tag> {

    void addTag(Tag tag);

    Object getlist(Integer pageNum, Integer pageSize);

    void deleteTag(List<Long> ids);

    TagVo getTag(Long id);

    void updateTag(Tag tag);


    Object getAlllist();
}

