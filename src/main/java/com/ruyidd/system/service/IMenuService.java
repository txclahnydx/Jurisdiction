package com.ruyidd.system.service;

import java.util.List;
import java.util.Map;

import com.ruyidd.system.entity.MenuEntity;

public interface IMenuService {
	
	int insert(MenuEntity menuEntity);
	
	void insert(List<MenuEntity> menuEntityList);
	
	int deleteMenuById(Long id);
	
	void updateMenuById(MenuEntity menuEntity);
	
	MenuEntity getMenuEntityById(Long id);
	
	List<MenuEntity> getMenuEntityList();
	
	List<MenuEntity> getMenuEntityList(Map<String,Object> conditions);
}
