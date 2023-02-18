package com.zhangjie.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.entity.Category;
import com.zhangjie.domain.vo.ExcelCategoryVo;
import com.zhangjie.enums.AppHttpCodeEnum;
import com.zhangjie.service.CategoryService;
import com.zhangjie.utils.BeanCopyUtils;
import com.zhangjie.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/content/category/listAllCategory")
    public ResponseResult<?> listAllCategory(){
        return ResponseResult.okResult(categoryService.listAllCategory());
    }

    @GetMapping("content/category/list")
    public ResponseResult<?> getCategoryList(Integer pageNum,Integer pageSize){
        return ResponseResult.okResult(categoryService.getCategoryList(pageNum, pageSize));
    }


    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/content/category/export")
    public void export(HttpServletResponse response){

        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }




}
