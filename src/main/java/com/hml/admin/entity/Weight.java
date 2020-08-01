package com.hml.admin.entity;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Insert;

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
@TableName("JL_Weight")
public class Weight implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField(value="Id",exist = false)
    private Integer Id;

    @TableField("Code")
    private String Code;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("MaterialName")
    private String MaterialName;

    @TableField("CarNumber")
    private String CarNumber;

    @TableField("Gross")
    private BigDecimal Gross;

    @TableField("Tare")
    private BigDecimal Tare;

    @TableField("Net")
    private BigDecimal Net;

    @TableField("BalanceWeight")
    private BigDecimal BalanceWeight;

    @TableField("OrderNo")
    private String OrderNo;
    
    @TableField("SaveTime")
    private String SaveTime;
}
