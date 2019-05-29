package com.jzt.sso.api;


import com.jzt.sso.model.RoleMenuEntity;
import com.jzt.sso.service.RoleMenuService;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("sys/rolemenu")
public class RoleMenuController {
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleMenuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RoleMenuEntity roleMenu = roleMenuService.selectById(id);

        return R.ok().put("roleMenu", roleMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RoleMenuEntity roleMenu){
        roleMenuService.insert(roleMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RoleMenuEntity roleMenu){
        ValidatorUtils.validateEntity(roleMenu);
        roleMenuService.updateAllColumnById(roleMenu);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        roleMenuService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
