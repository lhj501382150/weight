package com.hml.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.AppLogin;
import com.hml.admin.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
public interface IUserService extends IService<User> {
	
	User findByName(String username);
	
	User findByNameAndMobile(String username,String mobile);
	
	int saveAppLogin(AppLogin record);
}
