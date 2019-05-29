package com.jzt.sso.api;

import com.jzt.sso.annotation.SysLog;
import com.jzt.sso.dto.MenuDTO;
import com.jzt.sso.model.MenuEntity;
import com.jzt.sso.service.MenuService;
import com.jzt.sso.service.UserService;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.UserUtil;
import com.jzt.sso.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags="权限（菜单）管理模块")
@RestController
@RequestMapping("sys/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取权限（菜单）列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer",paramType="query"),
            @ApiImplicitParam(name = "limit", value = "页数量", dataType = "Integer",paramType="query")})
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @SysLog(value = "查看权限（菜单）列表",moduleName = "权限（菜单）模块")
    public R list(@RequestBody MenuDTO dto){
        Map<String, Object> params=new HashMap<>();
        params.put("page",dto.getPage().toString());
        params.put("limit",dto.getLimit().toString());
        PageUtils page = menuService.queryPage(params,dto);
        return R.ok().put("page", page);
    }



    @ApiOperation(value = "查看菜单信息")
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    @SysLog(value = "查看权限（菜单）详细信息",moduleName = "权限（菜单）模块")
    public R info(@PathVariable("id") Long id){
        MenuEntity menu = menuService.selectById(id);

        return R.ok().put("menu", menu);
    }

    @ApiOperation(value = "新增菜单")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @SysLog(value = "新增权限（菜单）",moduleName = "权限（菜单）模块")
    public R save(@RequestBody MenuEntity menu){
        menuService.insert(menu);
        return R.ok();
    }


    @ApiOperation(value = "修改菜单")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @SysLog(value = "修改权限（菜单）",moduleName = "权限（菜单）模块")
    public R update(@RequestBody MenuEntity menu){
        ValidatorUtils.validateEntity(menu);
        menuService.updateAllColumnById(menu);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除菜单")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @SysLog(value = "删除权限（菜单）",moduleName = "权限（菜单）模块")
    public R delete(@RequestBody Long[] ids){
        menuService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 列表
     */
    @ApiOperation(value = "获取菜单分组列表(不分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer",paramType="query"),
            @ApiImplicitParam(name = "limit", value = "页数量", dataType = "Integer",paramType="query")})
    @RequestMapping(value = "/groupList",method = RequestMethod.GET)
    @SysLog(value = "获取菜单分组列表(不分页)",moduleName = "权限（菜单）模块")
    public R groupList(){
        Long userId=userService.selectByName(UserUtil.getUserName()).getId();
        MenuDTO dto=new MenuDTO();
        dto.setPage(1);
        dto.setLimit(999);
        dto.setType(0);
        Map<String, Object> params=new HashMap<>();
        params.put("page",dto.getPage().toString());
        params.put("limit",dto.getLimit().toString());
        PageUtils page = menuService.queryPage(params,dto);
        MenuEntity orgMenu=menuService.selectById(3);
        List<MenuEntity> menuEntityList= (List<MenuEntity>) page.getList();
        if(userId!=1){
         menuEntityList.remove(orgMenu);
        }
        Map<String,List<MenuEntity>> map=new HashMap<>();
        for(MenuEntity menuEntity:menuEntityList){
            String menuName=menuEntity.getCode();
            String parMenuName="";
            if (menuEntity.getPid()==null){
                List<MenuEntity> result=new ArrayList<>();
                map.put(menuName,result);
            }
            if (menuEntity.getPid()!=null){
                parMenuName=menuService.selectById(menuEntity.getPid()).getCode();
                if(map.containsKey(parMenuName))
                {
                    map.get(parMenuName).add(menuEntity);
                }
               else {
                    List<MenuEntity> result=new ArrayList<>();
                    result.add(menuEntity);
                    map.put(parMenuName,result);
                }
            }

        }
        return R.ok().put("page", map);
    }

}
