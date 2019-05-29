package com.jzt.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.dto.CommonDTO;
import com.jzt.sso.model.RoleEntity;
import com.jzt.sso.utils.PageUtils;


import java.util.List;
import java.util.Map;


public interface RoleService extends IService<RoleEntity> {

    PageUtils queryPage(Map<String, Object> params, CommonDTO dto);

    public void saveRole();

    public List<Long> selectMenuIdByUserId(Long userId);

    public List<String> getRoleName(Long orgId, Long id);

}

