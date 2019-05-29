package com.jzt.sso.api;

import com.jzt.sso.model.UserMenuEntity;
import com.jzt.sso.service.UserMenuService;
import com.jzt.sso.utils.PageUtils;
import com.jzt.sso.utils.R;
import com.jzt.sso.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("sys/usermenu")
public class UserMenuController {
    @Autowired
    private UserMenuService userMenuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userMenuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UserMenuEntity userMenu = userMenuService.selectById(id);

        return R.ok().put("userMenu", userMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserMenuEntity userMenu){
        userMenuService.insert(userMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserMenuEntity userMenu){
        ValidatorUtils.validateEntity(userMenu);
        userMenuService.updateAllColumnById(userMenu);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        userMenuService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
