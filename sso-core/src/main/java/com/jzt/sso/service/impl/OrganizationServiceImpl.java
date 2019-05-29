package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jzt.sso.dto.OrgDTO;
import com.jzt.sso.mapper.OrganizationDao;
import com.jzt.sso.model.OrganizationEntity;
import com.jzt.sso.service.OrganizationService;
import com.jzt.sso.service.UserService;
import com.jzt.sso.utils.CommonUtils;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("organizationService")
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, OrganizationEntity> implements OrganizationService {

    @Autowired
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, OrgDTO dto) {
        OrganizationEntity model = new OrganizationEntity();
        EntityWrapper<OrganizationEntity> wrapper = new EntityWrapper<>(model);
        if (CommonUtils.notEmpty(dto.getOrgName())) {
            wrapper.like("org_name", dto.getOrgName());
        }
        if (CommonUtils.notEmpty(dto.getStatus())) {
            wrapper.eq("status",dto.getStatus() );
        }
        if (CommonUtils.notEmpty(dto.getUserId())&&dto.getUserId()!=1) {
            Long id=userService.selectById(dto.getUserId()).getOrgId();
            wrapper.eq("id",id );
        }
        Page<OrganizationEntity> page = this.selectPage(
                new Query<OrganizationEntity>(params).getPage(),
               wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<String> getOrgNames(Long id) {
        return this.baseMapper.getOrgNames(id);
    }

}
