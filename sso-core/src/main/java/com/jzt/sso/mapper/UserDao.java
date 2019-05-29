package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jzt.sso.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    public UserEntity selectByName(String username);

    public List<UserEntity> selectRoleUsers(Page<UserEntity> page, @Param("roleId") Long roleId);

    public List<UserEntity> selectUserIds(Page<UserEntity> page, Map<String, Object> params);
}
