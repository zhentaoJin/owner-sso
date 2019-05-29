package com.jzt.sso.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jzt.sso.annotation.SysLog;
import com.jzt.sso.dto.CommonDTO;
import com.jzt.sso.dto.RoleUserDTO;
import com.jzt.sso.model.*;
import com.jzt.sso.service.*;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags = "角色管理模块")
@RestController
@RequestMapping("sys/role")
public class RoleController {


    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @ApiOperation(value = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "页数量", dataType = "Integer", paramType = "query")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @SysLog(value = "查询角色列表", moduleName = "角色模块")
    public R list(@RequestBody CommonDTO dto) {
        if(dto.getLimit()==null){
            dto.setLimit(10);
        }
        Long orgId=userService.selectByName(UserUtil.getUserName()).getOrgId();
        Long userId=userService.selectByName(UserUtil.getUserName()).getId();
        dto.setOrgId(orgId);
        if(userId==1){
         dto.setOrgId(null);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("page", dto.getPage().toString());
        params.put("limit", dto.getLimit().toString());
        PageUtils page = roleService.queryPage(params,dto);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查看角色信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @SysLog(value = "查看角色信息", moduleName = "角色模块")
    public R info(@PathVariable("id") Long id) {
        RoleEntity role = roleService.selectById(id);
        List<Long> roleMenuEntityList = roleMenuService.selectByRoleId(id);
        List<MenuEntity> menuEntityList = menuService.selectBatchIds(roleMenuEntityList);
        role.setMenuEntityList(menuEntityList);
        Map<String, List<Long>> map = new HashMap<>();
        for (MenuEntity menuEntity : menuEntityList) {
            String menuName = menuEntity.getCode();
            String parMenuName = "";
            if (menuEntity.getPid() == null) {
                List<Long> result = new ArrayList<>();
                map.put(menuName, result);
            } else {
                parMenuName = menuService.selectById(menuEntity.getPid()).getCode();
                if (map.containsKey(parMenuName)) {
                    map.get(parMenuName).add(menuEntity.getId());
                } else {
                    List<Long> result = new ArrayList<>();
                    result.add(menuEntity.getId());
                    map.put(parMenuName, result);
                }
            }
        }
        return R.ok().put("role", role).put("menuIds", map);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @SysLog(value = "新增角色", moduleName = "角色模块")
    @Transactional
    public R save(@Valid @RequestBody RoleEntity role) {
        Long orgId=userService.selectByName(UserUtil.getUserName()).getOrgId();
        List<String> roleNameList = roleService.getRoleName(orgId,0L);
        if (roleNameList.size() > 0 && roleNameList.contains(role.getName())) {
            return R.error("该机构已经存在该角色名，角色名不能重复。");
        }
        roleService.insert(role);
        if (role.getMenuIds() == null||role.getMenuIds().size()==0) {
            return R.ok();
        }
        //分配菜单权限
        List<Long> menuIds = role.getMenuIds();
        List<RoleMenuEntity> roleMenuEntityList = new ArrayList<>(menuIds.size());
        for (Long menuEntity : menuIds) {
            RoleMenuEntity entity = new RoleMenuEntity();
            entity.setMenuId(menuEntity);
            entity.setRoleId(role.getId());
            roleMenuEntityList.add(entity);
        }
        roleMenuService.insertBatch(roleMenuEntityList);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @SysLog(value = "修改角色", moduleName = "角色模块")
    @Transactional
    public R update(@Valid @RequestBody RoleEntity role) {
        Long orgId=userService.selectByName(UserUtil.getUserName()).getOrgId();
        if (role.getId() == null) {
            return R.error("角色id不能为空。");
        }
        if (roleService.selectById(role.getId()) == null) {
            return R.error("没有找到对应的角色。");
        }
        List<String> roleNameList = roleService.getRoleName(orgId,role.getId());
        if (roleNameList.size() >0 && roleNameList.contains(role.getName())) {
            return R.error("该机构已经存在该角色名，角色名不能重复。");
        }
        roleService.updateAllColumnById(role);//全部更新
        roleMenuService.deleteByRoleId(role.getId());
        if(role.getMenuIds()!= null&&role.getMenuIds().size()>0){
            List<Long> menuIds = role.getMenuIds();
            List<RoleMenuEntity> roleMenuEntityList = new ArrayList<>(menuIds.size());
            for (Long menuEntity : menuIds) {
                RoleMenuEntity entity = new RoleMenuEntity();
                entity.setMenuId(menuEntity);
                entity.setRoleId(role.getId());
                roleMenuEntityList.add(entity);
            }
            roleMenuService.insertBatch(roleMenuEntityList);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @PreAuthorize("hasAnyAuthority('sys/role/delete')")
    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @SysLog(value = "删除角色", moduleName = "角色模块")
    public R delete(@RequestBody Long[] ids) {
        roleService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 角色添加用户
     */
    @ApiOperation(value = "指定角色添加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @SysLog(value = "指定角色添加用户", moduleName = "角色模块")
    public R addUser(@RequestBody RoleUserDTO dto) {
        if(dto.getUserIds()==null){
            return R.error("用户id不能为空。");
        }
        List<Long> userIds = Arrays.asList(dto.getUserIds());
        List<UserRoleEntity> result = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(dto.getRoleId());
            result.add(userRoleEntity);
        }
        userRoleService.insertBatch(result);
        return R.ok();
    }

    /**
     * 角色添加用户
     */
    @ApiOperation(value = "指定角色筛选用户")
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    @SysLog(value = "指定角色筛选用户", moduleName = "角色模块")
    public R selectUser(@RequestBody CommonDTO dto, HttpServletRequest request) {
        Page<UserEntity> result =new Page<>(dto.getPage(),dto.getLimit());
        String username=  UserUtil.getUserName();
        Long orgCode=userService.selectByName(username).getOrgId();
        Map<String,Object> map=new HashMap<>();
        map.put("roleId",dto.getRoleId());
        map.put("orgCode",orgCode);
        PageUtils userEntities=userService.selectUserIds(result,map);
        return R.ok().put("list",userEntities);
    }

    @ApiOperation(value = "角色删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @SysLog(value = "角色删除用户", moduleName = "角色模块")
    public R deleteUser(@RequestBody RoleUserDTO dto){
        List<Long> userIds=Arrays.asList(dto.getUserIds());
        for(Long userId:userIds){
            userRoleService.deleteByUserIdAndRoleId(dto.getRoleId(),userId);
        }
        return R.ok();
    }
}
