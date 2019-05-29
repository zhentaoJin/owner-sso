package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jzt.sso.model.SystemLogEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SystemLogDao extends BaseMapper<SystemLogEntity> {
	
}
