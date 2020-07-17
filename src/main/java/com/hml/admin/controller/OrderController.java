package com.hml.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
		QueryWrapper<Order> qw = new QueryWrapper<>();
		return HttpResult.ok(orderService.list(qw));
	}
	
	@PreAuthorize("hasAuthority('order:save')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Order record) {
		 
		return HttpResult.ok(orderService.saveOrUpdate(record));
	}
}

