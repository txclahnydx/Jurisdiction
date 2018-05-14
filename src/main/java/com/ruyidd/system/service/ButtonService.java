package com.ruyidd.system.service;

import java.util.List;
import java.util.Map;

import com.ruyidd.system.entity.ButtonEntity;

public interface ButtonService {

	int insert(ButtonEntity button);
	
	void insert(List<ButtonEntity> buttonList);
	
	void delete(Integer id);
	
	void delete(List<Integer> idList);
	
	void update(ButtonEntity button);
	
	ButtonEntity getButtonEntityById(Integer id);
	
	List<ButtonEntity> getButtonEntityList(Map<String, Object> conditions);

}
