package com.zhangjie.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjie.constants.SystemConstants;
import com.zhangjie.domain.entity.Link;
import com.zhangjie.domain.vo.LinkVo;
import com.zhangjie.mapper.LinkMapper;
import com.zhangjie.service.LinkService;
import com.zhangjie.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-01-16 01:53:48
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public Object getAllLink() {
        //查询所有审核通过的link
        LambdaQueryWrapper<Link> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(lambdaQueryWrapper);
        //封装成Vo
        return BeanCopyUtils.copyBeanList(links, LinkVo.class);
    }
}

