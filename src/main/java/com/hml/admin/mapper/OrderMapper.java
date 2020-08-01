package com.hml.admin.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hml.admin.entity.Order;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
public interface OrderMapper extends BaseMapper<Order> {

	@Select("select top 1 code from SaleOrders order by id desc ")
	public Order getMaxNo();
}
