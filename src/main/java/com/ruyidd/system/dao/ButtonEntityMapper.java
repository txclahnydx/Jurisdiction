package com.ruyidd.system.dao;

import com.ruyidd.system.entity.ButtonEntity;
import com.ruyidd.system.entity.ButtonEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ButtonEntityMapper {
    int countByExample(ButtonEntityExample example);

    int deleteByExample(ButtonEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ButtonEntity record);

    int insertSelective(ButtonEntity record);

    List<ButtonEntity> selectByExample(ButtonEntityExample example);

    ButtonEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ButtonEntity record, @Param("example") ButtonEntityExample example);

    int updateByExample(@Param("record") ButtonEntity record, @Param("example") ButtonEntityExample example);

    int updateByPrimaryKeySelective(ButtonEntity record);

    int updateByPrimaryKey(ButtonEntity record);
}