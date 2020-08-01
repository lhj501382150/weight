package com.hml.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hml.admin.entity.Customer;
import com.hml.admin.entity.Material;
import com.hml.admin.entity.Vendor;
import com.hml.admin.service.IDictService;
import com.hml.core.http.HttpResult;

@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {

	@Autowired
	private IDictService dictService;
	
	
	@PostMapping("/customer")
	public HttpResult findCustomer() {
		QueryWrapper<Customer> qw = new QueryWrapper<Customer>();
		qw.eq("TypeCode", "01");
		List<Customer> list = dictService.list(qw);
		List<String> temp = new ArrayList<String>();
		for(Customer custom:list) {
			temp.add(custom.getCode()+"-"+custom.getName());
		}
		return HttpResult.ok(temp);
	}
	@PostMapping("/vendor")
	public HttpResult findVendor() {
		QueryWrapper<Customer> qw = new QueryWrapper<Customer>();
		qw.eq("TypeCode", "02");
		List<Customer> list = dictService.list(qw);
		List<String> temp = new ArrayList<String>();
		for(Customer custom:list) {
			temp.add(custom.getCode()+"-"+custom.getName());
		}
		return HttpResult.ok(temp);
	}
	@PostMapping("/material")
	public HttpResult findMaterial() {
		List<Material> list = dictService.findMaterialList();
		List<String> temp = new ArrayList<String>();
		for(Material custom:list) {
			temp.add(custom.getCode()+"-"+custom.getName());
		}
		return HttpResult.ok(temp);
	}
	
	 
}
