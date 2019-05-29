package com.jzt.sso.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jzt.sso.model.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {

    public List<Long> selectRoleIdByUserId(Long userId);

    public void deleteByUserId(Long userId);

    public void deleteByUserIdAndRoleId(@Param(value = "roleId") Long roleId, @Param(value = "userId") Long userId);


}
