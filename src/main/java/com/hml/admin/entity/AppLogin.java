package com.hml.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppLogin implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("Code")
    private String Code;

    @TableField("Pwd")
    private String Pwd;

    @TableField("Mobile")
    private String Mobile;

    @TableField("Memo")
    private String Memo;


}
