package com.itlozg.admin.entity.dto;

import lombok.Data;

@Data
public class PageDto {
    //当前页码
    private String pageNum;

    //煤业多少条数据
    private String pageSize;
}
