package com.itlozg.admin.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itlozg.admin.annotation.ExcelField;
import com.itlozg.admin.entity.SignNext;
import com.itlozg.admin.entity.TPBaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SignNextDto extends SignNext implements Serializable {
    private String idcard;
    private String inTime;
    private String templateGroup;
    private String templateUser;
}
