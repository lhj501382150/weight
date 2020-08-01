package com.hml.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.Customer;
import com.hml.admin.entity.Material;
import com.hml.admin.entity.Vendor;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
public interface IDictService extends IService<Customer> {
	
	List<Vendor> findVendorList();
	
	List<Material> findMaterialList();
 
}
