package com.jzt.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.dto.OrgDTO;
import com.jzt.sso.model.OrganizationEntity;
import com.jzt.sso.utils.PageUtils;


import java.util.List;
import java.util.Map;


public interface OrganizationService extends IService<OrganizationEntity> {

    PageUtils queryPage(Map<String, Object> params, OrgDTO dto);

    public List<String> getOrgNames(Long id);
}

