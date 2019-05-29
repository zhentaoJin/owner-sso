package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jzt.sso.mapper.UserRoleDao;
import com.jzt.sso.model.UserRoleEntity;
import com.jzt.sso.service.UserRoleService;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserRoleEntity> page = this.selectPage(
                new Query<UserRoleEntity>(params).getPage(),
                new EntityWrapper<UserRoleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Long> selectRoleIdByUserId(Long userId) {
        return this.baseMapper.selectRoleIdByUserId(userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
     this.baseMapper.deleteByUserId(userId);
    }

    @Override
    public void deleteByUserIdAndRoleId(Long roleId, Long userId) {
        this.baseMapper.deleteByUserIdAndRoleId(roleId,userId);
    }


}
