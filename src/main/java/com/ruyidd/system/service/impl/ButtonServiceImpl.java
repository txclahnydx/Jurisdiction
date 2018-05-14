package com.ruyidd.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyidd.system.dao.ButtonEntityMapper;
import com.ruyidd.system.entity.ButtonEntity;
import com.ruyidd.system.entity.ButtonEntityExample;
import com.ruyidd.system.entity.ButtonEntityExample.Criteria;
import com.ruyidd.system.service.ButtonService;

@Service
public class ButtonServiceImpl implements ButtonService {
	
	@Autowired
	private ButtonEntityMapper buttonEntityMapper;

	@Override
	public int insert(ButtonEntity button) {
		return buttonEntityMapper.insertSelective(button);
	}

	@Override
	@Transactional
	public void insert(List<ButtonEntity> buttonList) {
		for (ButtonEntity buttonEntity : buttonList) {
			buttonEntityMapper.insertSelective(buttonEntity);
		}
	}

	@Override
	public void delete(Integer id) {
		buttonEntityMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void delete(List<Integer> idList) {
		for (Integer id : idList) {
			buttonEntityMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void update(ButtonEntity button) {
		buttonEntityMapper.updateByPrimaryKeySelective(button);
	}

	@Override
	public ButtonEntity getButtonEntityById(Integer id) {
		return buttonEntityMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ButtonEntity> getButtonEntityList(Map<String, Object> conditions) {
		ButtonEntityExample example = new ButtonEntityExample();
		if(conditions!=null) {
			if(conditions.containsKey("name")&&conditions.containsKey("id")) {
				Criteria criteria = example.createCriteria();
				criteria.andNameEqualTo(conditions.get("name").toString());
				criteria.andIdNotEqualTo(Integer.parseInt(conditions.get("id").toString()));
			}else if(conditions.containsKey("field")&&conditions.containsKey("id")) {
				Criteria criteria = example.createCriteria();
				criteria.andFieldEqualTo(conditions.get("field").toString());
				criteria.andIdNotEqualTo(Integer.parseInt(conditions.get("id").toString()));
			}else if(conditions.containsKey("name")) {
				Criteria criteria = example.createCriteria();
				criteria.andNameEqualTo(conditions.get("name").toString());
			}else if(conditions.containsKey("field")) {
				Criteria criteria = example.createCriteria();
				criteria.andFieldEqualTo(conditions.get("field").toString());
			}
		}
		return buttonEntityMapper.selectByExample(example);
	}

}
