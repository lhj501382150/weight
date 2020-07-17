package com.hml.admin.entity;

import java.math.BigDecimal;
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
@TableName("bl_order")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("Id")
    private Integer Id;

    @TableField("Code")
    private String Code;

    @TableField("BizType")
    private String BizType;

    @TableField("CarNumber")
    private String CarNumber;

    @TableField("MaterialCode")
    private String MaterialCode;

    @TableField("MaterialName")
    private String MaterialName;

    @TableField("CustomCode")
    private String CustomCode;

    @TableField("CustomName")
    private String CustomName;

    @TableField("OrderWeight")
    private BigDecimal OrderWeight;

    @TableField("Specification")
    private String Specification;

    @TableField("Driver")
    private String Driver;

    @TableField("Price")
    private BigDecimal Price;

    @TableField("Money")
    private BigDecimal Money;

    @TableField("CompleteWeight")
    private String CompleteWeight;

    @TableField("SaveTime")
    private String SaveTime;

    @TableField("OrderTime")
    private String OrderTime;

    @TableField("State")
    private Integer State;

    @TableField("Remark")
    private String Remark;


}
