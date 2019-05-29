package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jzt.sso.model.OrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrganizationDao extends BaseMapper<OrganizationEntity> {

    public List<String> getOrgNames(@Param(value = "id") Long id);
	
}
