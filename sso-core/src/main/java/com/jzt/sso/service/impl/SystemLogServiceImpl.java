package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jzt.sso.dto.LogDTO;
import com.jzt.sso.mapper.SystemLogDao;
import com.jzt.sso.model.SystemLogEntity;
import com.jzt.sso.service.SystemLogService;
import com.jzt.sso.utils.CommonUtils;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("systemLogService")
public class SystemLogServiceImpl extends ServiceImpl<SystemLogDao, SystemLogEntity> implements SystemLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, LogDTO dto) {
        SystemLogEntity model=new SystemLogEntity();
        EntityWrapper<SystemLogEntity> entityWrapper=new EntityWrapper<>(model);
        if (CommonUtils.notEmpty(dto.getOpAction())) {
            entityWrapper.like("op_org", dto.getOrgName());
        }
        if (CommonUtils.notEmpty(dto.getOpAction())) {
            entityWrapper.like("op_action", dto.getOpAction());
        }
        if (CommonUtils.notEmpty(dto.getOpName())) {
            entityWrapper.like("op_name",dto.getOpName() );
        }
        if (CommonUtils.notEmpty(dto.getOpEntity())) {
            entityWrapper.like("op_entity",dto.getOpEntity() );
        }
        if (CommonUtils.notEmpty(dto.getBeginDate())&&CommonUtils.notEmpty(dto.getEndDate())) {
            entityWrapper.between("create_time",dto.getBeginDate(),dto.getEndDate());
        }
        Page<SystemLogEntity> page = this.selectPage(
                new Query<SystemLogEntity>(params).getPage(),
                entityWrapper
        );

        return new PageUtils(page);
    }

}
