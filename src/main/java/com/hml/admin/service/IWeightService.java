package com.hml.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.Weight;
import com.hml.core.page.PageRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
public interface IWeightService extends IService<Weight> {
	List<Weight> findPage(PageRequest pageRequest);
}
