package com.itlozg.admin.entity.dto;

import lombok.Data;

@Data
public class GetSignInfoToPageDto extends PageDto {
    //起始时间
    private String startTime;
    //结束时间
    private String endTime;
    //用户名
    private String username;
    //部门名
    private String deptname;
}
