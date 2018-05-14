package com.ruyidd.system.dao;

import com.ruyidd.system.entity.MenuEntity;
import com.ruyidd.system.entity.MenuEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuEntityMapper {
    int countByExample(MenuEntityExample example);

    int deleteByExample(MenuEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MenuEntity record);

    int insertSelective(MenuEntity record);

    List<MenuEntity> selectByExample(MenuEntityExample example);

    MenuEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MenuEntity record, @Param("example") MenuEntityExample example);

    int updateByExample(@Param("record") MenuEntity record, @Param("example") MenuEntityExample example);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);
}