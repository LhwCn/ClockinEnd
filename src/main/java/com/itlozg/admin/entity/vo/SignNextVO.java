package com.itlozg.admin.entity.vo;

import com.itlozg.admin.entity.SignNext;
import lombok.Data;

import java.io.Serializable;

@Data
public class SignNextVO extends SignNext implements Serializable {
    private String userName;
    private String idcard;
    private String inTime;
    private String templateGroup;
    private String templateUser;
}
