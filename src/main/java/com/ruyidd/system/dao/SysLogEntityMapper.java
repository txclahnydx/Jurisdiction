package com.ruyidd.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ruyidd.system.entity.SysLogEntity;
import com.ruyidd.system.entity.SysLogEntityExample;
import com.ruyidd.system.entity.SysLogEntityWithBLOBs;

public interface SysLogEntityMapper {
    int countByExample(SysLogEntityExample example);

    int deleteByExample(SysLogEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysLogEntityWithBLOBs record);

    int insertSelective(SysLogEntityWithBLOBs record);

    List<SysLogEntityWithBLOBs> selectByExampleWithBLOBs(SysLogEntityExample example);

    List<SysLogEntity> selectByExample(SysLogEntityExample example);

    SysLogEntityWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysLogEntityWithBLOBs record, @Param("example") SysLogEntityExample example);

    int updateByExampleWithBLOBs(@Param("record") SysLogEntityWithBLOBs record, @Param("example") SysLogEntityExample example);

    int updateByExample(@Param("record") SysLogEntity record, @Param("example") SysLogEntityExample example);

    int updateByPrimaryKeySelective(SysLogEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysLogEntityWithBLOBs record);

    int updateByPrimaryKey(SysLogEntity record);
}