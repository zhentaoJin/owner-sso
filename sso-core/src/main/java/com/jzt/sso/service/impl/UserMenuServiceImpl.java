package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.jzt.sso.mapper.UserMenuDao;
import com.jzt.sso.model.UserMenuEntity;
import com.jzt.sso.service.UserMenuService;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userMenuService")
public class UserMenuServiceImpl extends ServiceImpl<UserMenuDao, UserMenuEntity> implements UserMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserMenuEntity> page = this.selectPage(
                new Query<UserMenuEntity>(params).getPage(),
                new EntityWrapper<UserMenuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Long> selectByUserId(Long userId) {
        return this.baseMapper.selectByUserId(userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        this.baseMapper.deleteByUserId(userId);
    }

}
