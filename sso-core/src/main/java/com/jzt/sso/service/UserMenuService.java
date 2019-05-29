package com.jzt.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.model.UserMenuEntity;
import com.jzt.sso.utils.PageUtils;


import java.util.List;
import java.util.Map;


public interface UserMenuService extends IService<UserMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public List<Long> selectByUserId(Long userId);

    public void deleteByUserId(Long userId);
}

