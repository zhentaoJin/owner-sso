package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jzt.sso.mapper.RoleMenuDao;
import com.jzt.sso.model.RoleMenuEntity;
import com.jzt.sso.service.RoleMenuService;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RoleMenuEntity> page = this.selectPage(
                new Query<RoleMenuEntity>(params).getPage(),
                new EntityWrapper<RoleMenuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Long> selectByRoleId(Long roleId) {
        return this.baseMapper.selectByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
         this.baseMapper.deleteByRoleId(roleId);
    }


}
