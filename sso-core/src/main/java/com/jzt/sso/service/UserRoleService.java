package com.jzt.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.model.UserRoleEntity;
import com.jzt.sso.utils.PageUtils;


import java.util.List;
import java.util.Map;

public interface UserRoleService extends IService<UserRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public List<Long> selectRoleIdByUserId(Long userId);

    public void deleteByUserId(Long userId);

    public void deleteByUserIdAndRoleId(Long roleId, Long userId);

}

