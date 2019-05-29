package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jzt.sso.model.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {

    public List<Long> selectMenuIdByUserId(Long userId);

    public List<String> getRoleName(@Param(value = "orgId") Long orgId, @Param(value = "id") Long id);
	
}
