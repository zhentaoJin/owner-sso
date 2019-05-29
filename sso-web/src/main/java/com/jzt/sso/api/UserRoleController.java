package com.jzt.sso.api;

import com.jzt.sso.model.UserRoleEntity;
import com.jzt.sso.service.UserRoleService;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("sys/userrole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UserRoleEntity userRole = userRoleService.selectById(id);

        return R.ok().put("userRole", userRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserRoleEntity userRole){
        userRoleService.insert(userRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserRoleEntity userRole){
        ValidatorUtils.validateEntity(userRole);
        userRoleService.updateAllColumnById(userRole);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        userRoleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
