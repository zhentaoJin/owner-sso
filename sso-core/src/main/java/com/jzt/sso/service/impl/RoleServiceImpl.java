package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jzt.sso.dto.CommonDTO;
import com.jzt.sso.mapper.RoleDao;
import com.jzt.sso.model.RoleEntity;
import com.jzt.sso.service.RoleService;
import com.jzt.sso.utils.CommonUtils;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, CommonDTO dto) {
        RoleEntity model =new RoleEntity();
        EntityWrapper<RoleEntity> wrapper=new EntityWrapper<>(model);
        if (CommonUtils.notEmpty(dto.getOrgId())) {
            wrapper.eq("org_Id", dto.getOrgId());
        }
        Page<RoleEntity> page = this.selectPage(
                new Query<RoleEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void saveRole() {

    }

    @Override
    public List<Long> selectMenuIdByUserId(Long userId) {
        return this.baseMapper.selectMenuIdByUserId(userId);
    }

    @Override
    public List<String> getRoleName(Long orgId,Long id) {
        return this.baseMapper.getRoleName(orgId,id);
    }


}
