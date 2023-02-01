package com.zhangjie.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjie.domain.entity.Tag;
import com.zhangjie.domain.vo.PageVo;
import com.zhangjie.domain.vo.TagListVo;
import com.zhangjie.domain.vo.TagVo;
import com.zhangjie.enums.AppHttpCodeEnum;
import com.zhangjie.exception.SystemException;
import com.zhangjie.mapper.TagMapper;
import com.zhangjie.service.TagService;
import com.zhangjie.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-01-31 15:48:41
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public void addTag(Tag tag) {
        //判断tag是否重复
        LambdaQueryWrapper<Tag> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Tag::getName,tag.getName());
        if(count(lambdaQueryWrapper)!=0){
            throw new SystemException(AppHttpCodeEnum.TAG_EXIST);
        }
        save(tag);
    }

    @Override
    public Object getlist(Integer pageNum, Integer pageSize) {
        //分页查询
        Page page = new Page(pageNum, pageSize);
        page(page);
        List<Tag> tags = page.getRecords();
        //转换成Vo
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(tags, TagVo.class);
        return new PageVo(tagVos,page.getTotal());
    }

    @Override
    public void deleteTag(List<Long> ids) {removeByIds(ids);}

    @Override
    public TagVo getTag(Long id) {
        Tag tag = getById(id);
        //转换成VO
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return tagVo;
    }

    @Override
    public void updateTag(Tag tag) {
        updateById(tag);
    }

    @Override
    public Object getAlllist() {
        List<Tag> list = list();
        List<TagListVo> tagListVos = BeanCopyUtils.copyBeanList(list, TagListVo.class);
        return tagListVos;
    }


}

