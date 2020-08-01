package com.hml.admin.controller;


import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hml.admin.entity.Order;
import com.hml.admin.service.IOrderService;
import com.hml.core.http.HttpResult;
import com.hml.core.page.PageRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
	
	@Autowired
	private IOrderService orderService;
	
	@PreAuthorize("hasAuthority('order:findAll')")
	@PostMapping("/findAll")
	public HttpResult findAll(@RequestBody PageRequest pageRequest)throws Exception{
		Map<String,Object> temp = new HashedMap();
		String bdate = "";
		String edate = "";
		Object o1 = pageRequest.getParam("bdate");
		if(o1 !=null && !"".equals(o1.toString())) {
			bdate = o1.toString().replaceAll("-", "");
		}
		Object o2 = pageRequest.getParam("edate");
		if(o2 !=null && !"".equals(o2.toString())) {
			bdate = o1.toString().replaceAll("-", "");
		}
		Object o3 = pageRequest.getParam("customerName");
		if(o3 !=null && !"".equals(o3.toString())) {
			temp.put("CustomerName@LIKE",o3.toString());
		}
		Object o4 = pageRequest.getParam("materialName");
		if(o4 !=null && !"".equals(o4.toString())) {
			temp.put("MaterialName@LIKE",o4.toString());
		}
		temp.put("CreateTime@GE",bdate);
		temp.put("CreateTime@LE",edate);
		pageRequest.setParams(temp);
		pageRequest.getParams().put("code@DESC","A");
		return HttpResult.ok(orderService.findPage(pageRequest));
	}
	
	@PreAuthorize("hasAuthority('order:save')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Order record) {
		if("".equals(record.getCustomerCode())) {
			return HttpResult.error("客户不能为空");
		}
		if("".equals(record.getVendorCode())) {
			return HttpResult.error("供货商不能为空");
		}
		if("".equals(record.getMaterialCode())) {
			return HttpResult.error("物料不能为空");
		}
		if(record.getTotal()==null) {
			return HttpResult.error("总量不能为空");
		}
		return HttpResult.ok(orderService.saveOrder(record));
	}
}

