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
import com.hml.admin.service.IWeightService;
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
@RequestMapping("/weight")
public class WeightController extends BaseController {
	
	@Autowired
	private IWeightService weightService;
	
	@PreAuthorize("hasAuthority('weight:findAll')")
	@PostMapping("/findAll")
	public HttpResult findAll(@RequestBody PageRequest pageRequest)throws Exception{
		Map<String,Object> temp = new HashedMap();
		String bdate = "";
		String edate = "";
		Object o1 = pageRequest.getParam("bdate");
		if(o1 !=null && !"".equals(o1.toString())) {
			bdate = o1.toString()+" 00:00:00";
		}
		Object o2 = pageRequest.getParam("edate");
		if(o2 !=null && !"".equals(o2.toString())) {
			bdate = o1.toString()+" 23:59:59";
		}
		Object o3 = pageRequest.getParam("customerName");
		if(o3 !=null && !"".equals(o3.toString())) {
			temp.put("CustomerName@LIKE",o3.toString());
		}
		Object o4 = pageRequest.getParam("materialName");
		if(o4 !=null && !"".equals(o4.toString())) {
			temp.put("MaterialName@LIKE",o4.toString());
		}
		temp.put("SaveTime@GE",bdate);
		temp.put("SaveTime@LE",edate);
		pageRequest.setParams(temp);
		pageRequest.getParams().put("code@DESC","A");
		return HttpResult.ok(weightService.findPage(pageRequest));
	}
	
	 
}

