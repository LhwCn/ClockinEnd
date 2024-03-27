package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("sign_group")
public class SignGroup extends TPBaseEntity implements Serializable {

    /**
     * 对象id
     */
    @TableField("id")
    private String id;


    /**
     * 打卡规则id
     */
    @TableField("rule_id")
    private String ruleId;


    /**
     * 考勤组部门
     * */
    @TableField("depart_name")
    private String departName;

    /**
     * 父节点id
     * @return
     */
    @TableField("parent_id")
    private String parentId;


}
