package com.example.webproject.core.common;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("分页信息")
public class CommonPage<T> {
    @ApiModelProperty("页数")
    private Integer pageNum;
    @ApiModelProperty("页大小")
    private Integer pageSize;
    @ApiModelProperty("总数")
    private Integer total;
    @ApiModelProperty("数据列表")
    private List<T> list;
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal((int) pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }
    @Override
    public String toString() {
        return "{" +
                "pageNum=" +pageNum+
                ",pageSize=" +pageSize+
                ",total=" +total+
                ",list=" +list+
                "}";
    }

//    public CommonPage() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    public CommonPage(Integer pageNum, Integer pageSize, Integer total, List<T> list) {
//        this.pageNum = pageNum;
//        this.pageSize = pageSize;
//        this.total = total;
//        this.list = list;
//    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
