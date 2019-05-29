package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jzt.sso.model.UserMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMenuDao extends BaseMapper<UserMenuEntity> {

    public List<Long> selectByUserId(Long userId);

    public void deleteByUserId(Long userId);
	
}
