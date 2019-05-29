package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jzt.sso.model.RoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenuEntity> {

    public List<Long> selectByRoleId(Long roleId);

    public void deleteByRoleId(Long roleId);
	
}
