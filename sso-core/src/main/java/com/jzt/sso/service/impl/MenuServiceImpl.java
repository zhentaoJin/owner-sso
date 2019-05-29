package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jzt.sso.dto.MenuDTO;
import com.jzt.sso.mapper.MenuDao;
import com.jzt.sso.model.MenuEntity;
import com.jzt.sso.service.MenuService;
import com.jzt.sso.utils.CommonUtils;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, MenuDTO dto) {
        MenuEntity model = new MenuEntity();
        EntityWrapper<MenuEntity> wrapper = new EntityWrapper<>(model);
        if (CommonUtils.notEmpty(dto.getName())) {
            wrapper.like("name", dto.getName());
        }
        if (CommonUtils.notEmpty(dto.getCode())) {
            wrapper.like("code", dto.getCode());
        }
        if (CommonUtils.notEmpty(dto.getType())) {
            wrapper.like("type", dto.getType().toString());
        }
        Page<MenuEntity> page = this.selectPage(
                new Query<MenuEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
