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
@TableName("SaleOrders")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("Id")
    private Integer Id;

    @TableField("Code")
    private String Code;

    @TableField("CustomerCode")
    private String CustomerCode;

    @TableField("CustomerName")
    private String CustomerName;

    @TableField("VendorCode")
    private String VendorCode;

    @TableField("Vendor")
    private String Vendor;

    @TableField("MaterialID")
    private String MaterialID;

    @TableField("MaterialCode")
    private String MaterialCode;

    @TableField("MaterialName")
    private BigDecimal MaterialName;

    @TableField("SpecificationsModel")
    private String SpecificationsModel;

    @TableField("Total")
    private BigDecimal Total;

    @TableField("SendCount")
    private BigDecimal SendCount;

    @TableField("Count")
    private BigDecimal Count;

    @TableField("CreateTime")
    private String CreateTime;

    @TableField("SaveTime")
    private String SaveTime;

    @TableField("ReturnSign")
    private String ReturnSign;

    @TableField("IsComplete")
    private Integer IsComplete;

    @TableField("Operator")
    private String Operator;
    
    @TableField("OperatorCode")
    private String OperatorCode;
    
    @TableField("BizType")
    private String BizType;
    
    @TableField("EnableFlag")
    private Integer EnableFlag;
    
    @TableField("Warning")
    private Double Warning;

    

}
