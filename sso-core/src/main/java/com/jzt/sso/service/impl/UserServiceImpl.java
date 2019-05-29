package com.jzt.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jzt.sso.dto.CommonDTO;
import com.jzt.sso.dto.UserDTO;
import com.jzt.sso.mapper.UserDao;
import com.jzt.sso.model.UserEntity;
import com.jzt.sso.model.UserMenuEntity;
import com.jzt.sso.model.UserRoleEntity;
import com.jzt.sso.service.RoleService;
import com.jzt.sso.service.UserMenuService;
import com.jzt.sso.service.UserRoleService;
import com.jzt.sso.service.UserService;
import com.jzt.sso.utils.CommonUtils;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    private ShaPasswordEncoder shaPasswordEncoder(){
        return  new ShaPasswordEncoder();
    }

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    private RoleService roleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, CommonDTO dto) {
        UserEntity model = new UserEntity();
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>(model);
        if (CommonUtils.notEmpty(dto.getPhone())) {
            wrapper.like("phone", dto.getPhone());
        }
        if (CommonUtils.notEmpty(dto.getUsername())) {
            wrapper.like("username", dto.getUsername());
        }
        if (CommonUtils.notEmpty(dto.getOrgId())) {
            wrapper.eq("org_code", dto.getOrgId());
        }
        if (CommonUtils.notEmpty(dto.getStatus())) {
            wrapper.eq("status", dto.getStatus());
        }
        Page<UserEntity> page = this.selectPage(
                new Query<UserEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserDTO userEntity) {
        UserEntity entity= new UserEntity();
        BeanUtils.copyProperties(userEntity,entity);
        entity.setPassword(shaPasswordEncoder().encodePassword(entity.getPassword(),null));
        entity.setOrgId(userEntity.getOrgCode());
        List<Long> roleIds=Arrays.asList(userEntity.getRoleIds());
        entity.setRoleId(roleIds.get(0));
        entity.setRoleName(roleService.selectById(roleIds.get(0)).getName());
        this.baseMapper.insert(entity);

        insertOrUpdateRole(roleIds,entity.getId());

        if(userEntity.getMenuList()!=null&&userEntity.getMenuList().length>0){
            List<Long> menuIds= Arrays.asList(userEntity.getMenuList());
            insertOrUpdateMenu(menuIds,entity.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDTO userEntity) {
        UserEntity entity=new UserEntity();
        BeanUtils.copyProperties(userEntity,entity);
        //密码不修改
//        entity.setPassword(this.baseMapper.selectById(entity.getId()).getPassword());
//        entity.setPassword(shaPasswordEncoder().encodePassword(entity.getPassword(),null));
        if(userEntity.getRoleIds()!=null&&userEntity.getRoleIds().length>0)
        {
            userRoleService.deleteByUserId(userEntity.getId());
            List<Long> roleIds=Arrays.asList(userEntity.getRoleIds());
            entity.setRoleId(roleIds.get(0));
            entity.setRoleName(roleService.selectById(roleIds.get(0)).getName());
            insertOrUpdateRole(roleIds,userEntity.getId());
        }
        this.updateById(entity);//全部更新
        if(userEntity.getMenuList()!=null&&userEntity.getMenuList().length>0){
            userMenuService.deleteByUserId(userEntity.getId());
            List<Long> menuIds= Arrays.asList(userEntity.getMenuList());
            insertOrUpdateMenu(menuIds,userEntity.getId());
        }
    }

    @Override
    public PageUtils queryUser(Map<String, Object> params) {
        Page<UserEntity> page = this.selectPage(
                new Query<UserEntity>(params).getPage(),
                new EntityWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserEntity selectByName(String username) {
        return this.baseMapper.selectByName(username);
    }

    @Override
    public PageUtils selectRoleUsers(Page<UserEntity> page, Map<String, Object> params) {
        Long roleId= (Long) params.get("roleId");
        List<UserEntity> list=this.baseMapper.selectRoleUsers(page,roleId);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectUserIds(Page<UserEntity> page, Map<String, Object> params) {
         List<UserEntity> list=this.baseMapper.selectUserIds(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }


    private void insertOrUpdateRole(List<Long> roleIds,Long userId) {
        List<UserRoleEntity> userRoleEntities = new ArrayList<>(roleIds.size());
        for (Long roleId : roleIds) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRoleId(roleId);
            userRoleEntity.setUserId(userId);
            userRoleEntities.add(userRoleEntity);
        }
        userRoleService.insertBatch(userRoleEntities);
    }

    private void insertOrUpdateMenu(List<Long> menuIds,Long userId) {
        List<UserMenuEntity> userMenuEntityList=new ArrayList<>(menuIds.size());
        for(Long menuId :menuIds){
            UserMenuEntity userMenuEntity=new UserMenuEntity();
            userMenuEntity.setMenuId(menuId);
            userMenuEntity.setUserId(userId);
            userMenuEntityList.add(userMenuEntity);
        }
        userMenuService.insertBatch(userMenuEntityList);
    }

}
