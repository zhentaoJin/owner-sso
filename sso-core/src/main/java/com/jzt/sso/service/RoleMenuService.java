package com.jzt.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.model.RoleMenuEntity;
import com.jzt.sso.utils.PageUtils;


import java.util.List;
import java.util.Map;


public interface RoleMenuService extends IService<RoleMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public List<Long> selectByRoleId(Long roleId);

    public void deleteByRoleId(Long roleId);
}

