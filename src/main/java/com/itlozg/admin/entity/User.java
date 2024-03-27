package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lms
 * @since 2023-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("admin_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 所属公司
     */
    private String company;

    /**
     * 部门
     */
    private String department;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * (管理员状态,0代表正常,1代表禁止,,默认为0
     */
    private Integer status;

    /**
     * (管理员删除,0代表未删除,1代表删除,默认为0)
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 页码
     */
    @TableField(exist = false)
    private Integer pageNum;

    /**
     * 每页个数
     */
    @TableField(exist = false)
    private Integer pageSize;

    /**
     * ID组
     */
    @TableField(exist = false)
    private List<Integer> idList;

    /**
     * 角色id组
     */
    @TableField(exist = false)
    private List<Integer> roleIdList;
}
