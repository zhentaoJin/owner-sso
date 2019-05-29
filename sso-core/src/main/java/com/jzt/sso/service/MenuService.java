package com.jzt.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.dto.MenuDTO;
import com.jzt.sso.model.MenuEntity;
import com.jzt.sso.utils.PageUtils;

import java.util.Map;


public interface MenuService extends IService<MenuEntity> {

    PageUtils queryPage(Map<String, Object> params, MenuDTO dto);
}

