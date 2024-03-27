package com.itlozg.admin.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 补卡申请记录
 */
@Data
@TableName("sign_repair_apply")
public class SignRepairApply {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    @TableField("apply_no")
    private String applyNo;

    @TableField("apply_name")
    private String applyName;

    @TableField("apply_time")
    private String applyTime;

    @TableField("apply_repair_date")
    private String applyRepairDate;

    @TableField("check_no")
    private String checkNo;

    @TableField("check_name")
    private String checkName;

    @TableField("check_time")
    private String checkTime;

    @TableField("check_status")
    private String checkStatus;

    @TableField("remarks")
    private String remarks;
}
