package com.hml.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.Weight;
import com.hml.admin.mapper.WeightMapper;
import com.hml.admin.service.IDictService;
import com.hml.admin.service.IWeightService;
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
public class WeightServiceImpl extends ServiceImpl<WeightMapper, Weight> implements IWeightService {
	
	@Autowired
	private WeightMapper weightMapper;
	
	 @Override
	public List<Weight> findPage(PageRequest pageRequest) {
		 PageResult pr = MybatisPlusPageHelper.findPage(pageRequest, weightMapper);
		 
		return (List<Weight>)pr.getContent();
	}
}
