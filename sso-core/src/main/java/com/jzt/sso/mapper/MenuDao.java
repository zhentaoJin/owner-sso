package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jzt.sso.model.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {
	
}
