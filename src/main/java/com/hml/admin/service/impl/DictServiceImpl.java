package com.hml.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.Customer;
import com.hml.admin.entity.Material;
import com.hml.admin.entity.Vendor;
import com.hml.admin.mapper.CustomerMapper;
import com.hml.admin.mapper.MaterialMapper;
import com.hml.admin.mapper.VendorMapper;
import com.hml.admin.service.IDictService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
@Service
public class DictServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements IDictService {
	
	@Autowired
	private VendorMapper vendorMapper;
	
	@Autowired
	private MaterialMapper materialMapper;
	
	
	@Override
	public List<Material> findMaterialList() {
		List<Material> list = materialMapper.selectList(null);
		return list;
	}
	
	@Override
	public List<Vendor> findVendorList() {
		List<Vendor> list = vendorMapper.selectList(null);
		return list;
	}
}
