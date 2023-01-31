package com.zhangjie.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo implements Serializable {

        private Long id;
        //标题
        private String title;
        //访问量
        private Long viewCount;

}
