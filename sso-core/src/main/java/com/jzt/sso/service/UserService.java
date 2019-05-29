package com.jzt.sso.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.dto.CommonDTO;
import com.jzt.sso.dto.UserDTO;
import com.jzt.sso.model.UserEntity;
import com.jzt.sso.utils.PageUtils;


import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params, CommonDTO dto);

    public void saveUser(UserDTO userEntity);

    public void updateUser(UserDTO userEntity);

    public PageUtils queryUser(Map<String, Object> params);

    public UserEntity selectByName(String username);

    public PageUtils selectRoleUsers(Page<UserEntity> page, Map<String, Object> params);

    public PageUtils selectUserIds(Page<UserEntity> page, Map<String, Object> params);
}

