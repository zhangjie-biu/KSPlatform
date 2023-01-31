package com.zhangjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjie.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-01-16 01:53:47
 */
public interface LinkService extends IService<Link> {

    Object getAllLink();
}

