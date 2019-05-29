package com.jzt.sso.api;

import com.baomidou.mybatisplus.plugins.Page;

import com.jzt.sso.annotation.SysLog;
import com.jzt.sso.dto.CommonDTO;
import com.jzt.sso.dto.PasswordDTO;
import com.jzt.sso.dto.StatusDTO;
import com.jzt.sso.dto.UserDTO;
import com.jzt.sso.model.MenuEntity;
import com.jzt.sso.model.RoleEntity;
import com.jzt.sso.model.UserEntity;
import com.jzt.sso.service.*;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.UserUtil;
import com.jzt.sso.utils.ValidataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags="用户管理模块")
@RestController
@RequestMapping("sys/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ShaPasswordEncoder shaPasswordEncoder(){
        return  new ShaPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserMenuService userMenuService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

  private BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 列表
     */
    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer",paramType="query"),
            @ApiImplicitParam(name = "limit", value = "页数量", dataType = "Integer",paramType="query")})
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @SysLog(value = "查看用户列表",moduleName = "用户模块")
    public R list(@RequestBody CommonDTO dto){
        if(dto.getLimit()==null){
            dto.setLimit(10);
        }
        Map<String, Object> params=new HashMap<>();
        params.put("page",dto.getPage().toString());
        params.put("limit",dto.getLimit().toString());
        PageUtils page = userService.queryPage(params,dto);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查看用户信息")
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    @SysLog(value = "查看用户详情",moduleName = "用户模块")
    public R info(@PathVariable("id") Long id){
        UserEntity user = userService.selectById(id);
        List<MenuEntity> systemList=menuService.selectBatchIds(userMenuService.selectByUserId(id));
        user.setMenuEntityList(systemList);
        List<Long> menuIds=roleService.selectMenuIdByUserId(id);
        List<Long> roleIds=userRoleService.selectRoleIdByUserId(id);
        List<RoleEntity> roleEntities=roleService.selectBatchIds(roleIds);
        List<MenuEntity> menuList=menuService.selectBatchIds(menuIds);
        return R.ok().put("user", user).put("roleList",roleEntities).put("menuList",menuList);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @SysLog(value = "新增用户",moduleName = "用户模块")
    @Transactional
    public R save(@Valid @RequestBody UserDTO dto){
        boolean phone= ValidataUtil.isPhoneNumber(dto.getPhone());
         if(phone==false){
             return R.error("手机号码格式不正确。");
         }
         if(dto.getRoleIds()==null){
             return R.error("角色id不能为空。");
         }
        if(dto.getMenuList()==null){
            return R.error("业务系统id不能为空。");
        }
        userService.saveUser(dto);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改用户")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @SysLog(value = "修改用户信息",moduleName = "用户模块")
    @Transactional
    public R update(@Valid @RequestBody UserDTO dto){
        boolean phone=ValidataUtil.isPhoneNumber(dto.getPhone());
        if(phone==false){
            return R.error("手机号码格式不正确。");
        }
        if(dto.getRoleIds()==null){
            return R.error("角色id不能为空。");
        }
        if(dto.getMenuList()==null){
            return R.error("业务系统id不能为空。");
        }
        userService.updateUser(dto);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @SysLog(value = "删除用户",moduleName = "用户模块")
    public R delete(@RequestBody Long[] ids){
        userService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    @SysLog(value = "修改用户密码",moduleName = "用户模块")
    public R updatePassword(@RequestBody PasswordDTO dto){
        Long userId=userService.selectByName(UserUtil.getUserName()).getId();
        UserEntity user=userService.selectById(userId);
        String oldPassWord=user.getPassword();
        if(!oldPassWord.equals(shaPasswordEncoder().encodePassword(dto.getOldPassword(),null))){
            return R.error("旧密码错误，请检查。");
        }
        String newPassword= shaPasswordEncoder().encodePassword(dto.getNewPassword(),null);
        user.setPassword(newPassword);
        userService.updateAllColumnById(user);//全部更新
        return R.ok();
    }

    @ApiOperation(value = "启用或禁用用户")
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @SysLog(value = "启用或禁用用户",moduleName = "用户模块")
    public R updateStatus(@RequestBody StatusDTO dto){
        Long userId=userService.selectByName(UserUtil.getUserName()).getId();
        UserEntity user=userService.selectById(userId);
        user.setStatus(dto.getStatus());
        userService.updateAllColumnById(user);//全部更新
        return R.ok();
    }

    @ApiOperation(value = "根据手机号查看用户信息")
    @RequestMapping(value = "detail/{username}",method = RequestMethod.GET)
    @SysLog(value = "根据手机号查询用户",moduleName = "用户模块")
    public R info(@PathVariable("username") String  username){
        UserEntity user = userService.selectByName(username);
        return R.ok().put("user", user);
    }


    @ApiOperation(value = "查询角色所有用户")
    @RequestMapping(value = "/roleusers",method = RequestMethod.POST)
    @SysLog(value = "查询角色所有用户",moduleName = "角色模块")
    public R userAndRole(@RequestBody CommonDTO dto){
        Page<UserEntity> result =new Page<>(dto.getPage(),dto.getLimit());
        Map<String, Object> params=new HashMap<>();
        params.put("roleId",dto.getRoleId());
        PageUtils page = userService.selectRoleUsers(result,params);
        return R.ok().put("page", page);
    }

    @ApiOperation(value = "用户查看角色信息")
    @RequestMapping(value = "/roleInfo/{id}", method = RequestMethod.GET)
    @SysLog(value = "查看角色信息", moduleName = "用户模块")
    public R roleInfo(@PathVariable("id") Long id) {
        RoleEntity role = roleService.selectById(id);
        List<Long> roleMenuEntityList = roleMenuService.selectByRoleId(id);
        List<MenuEntity> menuEntityList = menuService.selectBatchIds(roleMenuEntityList);
        role.setMenuEntityList(menuEntityList);
        return R.ok().put("role", role);
    }

    @ApiOperation(value = "机构筛选角色")
    @RequestMapping(value = "/orgRole/{orgId}", method = RequestMethod.GET)
    @SysLog(value = "机构筛选角色", moduleName = "用户模块")
    public R orgRole(@PathVariable("orgId") Long orgId) {
        CommonDTO dto=new CommonDTO();
        dto.setOrgId(orgId);
        Map<String,Object> map=new HashMap<>();
        map.put("page","1");
        map.put("limit","999");
        List<RoleEntity> roleList= (List<RoleEntity>) roleService.queryPage(map,dto).getList();
        return R.ok().put("role", roleList);
    }

    @ApiOperation(value = "角色查询菜单")
    @RequestMapping(value = "/roleMenu",method = RequestMethod.POST)
    @SysLog(value = "角色查询菜单",moduleName = "用户模块")
    public R menuList(@RequestBody Long[] ids){
        List<MenuEntity> menuEntityList =new ArrayList<>();
        if(ids==null||ids.length==0){
            return R.ok().put("menuList",menuEntityList);
        }
        List<Long> roleIds=Arrays.asList(ids);
        Set<Long> menuIds=new HashSet<>();
        for(Long roleId:roleIds){
            menuIds.addAll(roleMenuService.selectByRoleId(roleId));
        }
        List<Long> result = new ArrayList<>(menuIds);
        menuEntityList=menuService.selectBatchIds(result);
        return R.ok().put("menuList",menuEntityList);
    }

}
