package com.ruyidd.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.ruyidd.system.dao.MenuEntityMapper;
import com.ruyidd.system.entity.MenuEntity;
import com.ruyidd.system.entity.MenuEntityExample;
import com.ruyidd.system.service.IMenuService;

public class MenuServiceImpl implements IMenuService {
	
	@Autowired
	private MenuEntityMapper menuEntityMapper;

	@Override
	public int insert(MenuEntity menuEntity) {
		return menuEntityMapper.insertSelective(menuEntity);
	}

	@Override
	@Transactional
	public void insert(List<MenuEntity> menuEntityList) {
		for (MenuEntity menuEntity : menuEntityList) {
			menuEntityMapper.insertSelective(menuEntity);
		}
	}

	@Override
	public int deleteMenuById(Long id) {
		return menuEntityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateMenuById(MenuEntity menuEntity) {
		menuEntityMapper.updateByPrimaryKey(menuEntity);
	}

	@Override
	public MenuEntity getMenuEntityById(Long id) {
		return menuEntityMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<MenuEntity> getMenuEntityList() {
		MenuEntityExample example = new MenuEntityExample();
		example.setOrderByClause("order by sort asc");
		return menuEntityMapper.selectByExample(example);
	}

	@Override
	public List<MenuEntity> getMenuEntityList(Map<String, Object> conditions) {
		// TODO Auto-generated method stub
		return null;
	}


}
