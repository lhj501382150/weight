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
@TableName("Custom")
public class Vendor implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("Id")
    private Integer Id;

    @TableField("Code")
    private String Code;

    @TableField("FName")
    private String Name;
}
