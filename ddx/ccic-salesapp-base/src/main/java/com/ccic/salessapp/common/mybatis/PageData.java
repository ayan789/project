package com.ccic.salessapp.common.mybatis;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@ApiModel(value = "分页数据", description = "分页数据")
public class PageData<T> {
    @ApiModelProperty(value = "数据集合", required = true)
    private List<T> content;
    @ApiModelProperty(value = "总页数", required = true)
    private int totalPages;
    @ApiModelProperty(value = "总条数", required = true)
    private long totalElements;

    private PageData(List<T> content, int totalPages, long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    private PageData() {}
    public static <T> PageData of(List<T> content,long totalElements,int totalPages) {
        return new PageData(content ,totalPages, totalElements);
    }

    public static <F,T> PageData from(PageData<F> pageData,Function<F,T> function ) {
        return new PageData(pageData.getContent().stream().map(function).collect(Collectors.toList()) ,pageData.getTotalPages(),pageData.getTotalElements());
    }

    //pageInfo 轉換成pageData
    public static <F,T> PageData from(PageInfo<F> pageData, Function<F,T> function ) {
        return new PageData(pageData.getList().stream().map(function).collect(Collectors.toList()) ,pageData.getPages(),pageData.getTotal());
    }

    //pageInfo 轉換成pageData
    public static <F> PageData from(PageInfo<F> pageData) {
        return new PageData(pageData.getList() ,pageData.getPages(),pageData.getTotal());
    }
}
