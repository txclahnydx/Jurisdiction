package com.ruyidd.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyidd.system.dao.UserEntityMapper;
import com.ruyidd.system.entity.UserEntity;
import com.ruyidd.system.entity.UserEntityExample;
import com.ruyidd.system.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserEntityMapper userMapper;

	@Override
	public int insert(UserEntity user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public void insert(List<UserEntity> userList) {
		for (UserEntity user : userList) {
			System.out.println(userMapper.insertSelective(user));
		}
		
	}

	@Override
	public UserEntity getUserByUserId(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<UserEntity> loadUserList() {
		UserEntityExample example = new UserEntityExample();
		example.setOrderByClause("create_date desc");
		return userMapper.selectByExample(example);
	}

}
