package com.hml.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.User;
import com.hml.admin.mapper.UserMapper;
import com.hml.admin.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public User findByName(String username) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.eq("Code", username);
		User user = userMapper.selectOne(qw);
		return user;
	}
}
