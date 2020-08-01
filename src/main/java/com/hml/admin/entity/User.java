package com.hml.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bl_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("Id")
    private Integer Id;

    @TableField("Code")
    private String Code;

    @TableField("OrganCode")
    private String OrganCode;

    @TableField("RoleCode")
    private String RoleCode;

    @TableField("FName")
    private String FName;

    @TableField("FPassword")
    private String FPassword;

    @TableField("Remark")
    private String Remark;

    @TableField("SaveTime")
    private String SaveTime;

    @TableField("Isupped")
    private Integer Isupped;


}
