package com.hml.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.AppLogin;
import com.hml.admin.entity.User;
import com.hml.admin.mapper.AppLoginMapper;
import com.hml.admin.mapper.UserMapper;
import com.hml.admin.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-07-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AppLoginMapper appLoginMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public User findByNameAndMobile(String username, String mobile) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.eq("Code", username);
		//qw.eq("MobileCode", mobile);
		User user = userMapper.selectOne(qw);
		return user;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public User findByName(String username) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.eq("Code", username);
		User user = userMapper.selectOne(qw);
		if(user !=null ){
			QueryWrapper<AppLogin> qw1 = new QueryWrapper<>();
			qw1.eq("Code", username);
			AppLogin login = appLoginMapper.selectOne(qw1);
			if(login!=null){
				user.setFPassword(login.getPwd());
			}else{
				user.setFPassword("");
			}
		}
		return user;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int saveAppLogin(AppLogin record) {
		QueryWrapper<AppLogin> qw = new QueryWrapper<>();
		qw.eq("Code", record.getCode());
		AppLogin al = appLoginMapper.selectOne(qw);
		if(al!=null){
			record.setId(al.getId());
			return appLoginMapper.updateById(record);
		}else{
			return appLoginMapper.insert(record);
		}
	}
	
	
	
}
