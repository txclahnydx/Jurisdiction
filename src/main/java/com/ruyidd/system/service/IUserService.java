package com.ruyidd.system.service;

import java.util.List;

import com.ruyidd.system.entity.UserEntity;

public interface IUserService {
	
	 int insert(UserEntity user);
	 
	 void insert(List<UserEntity> userList);
	 
	 UserEntity getUserByUserId(Long userId);
	 
	 List<UserEntity> loadUserList();

}
