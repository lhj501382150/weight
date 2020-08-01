package com.hml.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.Material;
import com.hml.admin.entity.Order;
import com.hml.admin.mapper.MaterialMapper;
import com.hml.admin.mapper.OrderMapper;
import com.hml.admin.service.IOrderService;
import com.hml.admin.util.SecurityUtils;
import com.hml.core.page.MybatisPlusPageHelper;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private MaterialMapper materialMapper;
	
	@Override
	public List<Order> findPage(PageRequest pageRequest) {
		PageResult pr = MybatisPlusPageHelper.findPage(pageRequest, orderMapper);
		
		return (List<Order>)pr.getContent();
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int saveOrder(Order order) {
		Order o = orderMapper.getMaxNo();
		String curDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String code = o.getCode();
		String temp = code.substring(3, 11);
		String f_code = "";
		if(curDate.equals(temp)) {
			Integer no = Integer.parseInt(code.substring(11))+1;
			f_code = "S01" + temp + String.format("%04d", no);
		}else {
			f_code = "S01"+ curDate + "0001";
		}
		
		order.setCode(f_code);
		String[] c = order.getCustomerCode().split("-");
		order.setCustomerCode(c[0]);
		order.setCustomerName(c[1]);
		String[] v = order.getVendorCode().split("-");
		order.setVendorCode(v[0]);
		order.setVendor(v[1]);
		 
		String[] m = order.getMaterialCode().split("-");
		order.setMaterialCode(m[0]);
		order.setMaterialName(m[1]);
		
		order.setCreateTime(curDate);
		order.setSaveTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		order.setReturnSign(0);
		order.setIsComplete(0);
		order.setOperator(SecurityUtils.getUsername());
		order.setOperatorCode(SecurityUtils.getUsername());
		//order.set	("S");
		order.setEnableFlag(0);
		order.setAdjust(0);
		order.setSpecificationsModel("");
		
		orderMapper.insert(order);
		
		return 0;
	}
	
	
	private Material getMateria(String m) {
		String[] ms = m.split("-");
		QueryWrapper<Material> qw = new QueryWrapper<>();
		qw.eq("Code", ms[0]);
		
		Material material = materialMapper.selectOne(qw);
		return material;
	}
}
