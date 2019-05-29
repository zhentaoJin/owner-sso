package com.jzt.sso.service;

import com.baomidou.mybatisplus.service.IService;
import com.jzt.sso.dto.LogDTO;
import com.jzt.sso.model.SystemLogEntity;
import com.jzt.sso.utils.PageUtils;


import java.util.Map;


public interface SystemLogService extends IService<SystemLogEntity> {

    PageUtils queryPage(Map<String, Object> params, LogDTO dto);
}

