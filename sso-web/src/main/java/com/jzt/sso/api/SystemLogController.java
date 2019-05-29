package com.jzt.sso.api;

import com.jzt.sso.dto.LogDTO;
import com.jzt.sso.model.SystemLogEntity;
import com.jzt.sso.service.SystemLogService;
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


@Api(tags="日志管理模块")
@RestController
@RequestMapping("sys/systemlog")
public class SystemLogController {
    @Autowired
    private SystemLogService systemLogService;
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @ApiOperation(value = "获取日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer",paramType="query"),
            @ApiImplicitParam(name = "limit", value = "页数量", dataType = "Integer",paramType="query")})
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public R list(@RequestBody LogDTO dto){
        if(dto.getLimit()==null){
            dto.setLimit(10);
        }
      String orgName=userService.selectByName(UserUtil.getUserName()).getOrgName();
      dto.setOrgName(orgName);
        if(dto.getDates()!=null&&dto.getDates().size()>1){
            dto.setBeginDate(dto.getDates().get(0));
            dto.setEndDate(dto.getDates().get(1));
        }
        Map<String, Object> params=new HashMap<>();
        params.put("page",dto.getPage().toString());
        params.put("limit",dto.getLimit().toString());
        PageUtils page = systemLogService.queryPage(params,dto);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查看日志信息")
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public R info(@PathVariable("id") Long id){
        SystemLogEntity systemLog = systemLogService.selectById(id);
        return R.ok().put("systemLog", systemLog);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增日志")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public R save(@RequestBody SystemLogEntity systemLog){
        systemLogService.insert(systemLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改日志")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public R update(@RequestBody SystemLogEntity systemLog){
        ValidatorUtils.validateEntity(systemLog);
        systemLogService.updateAllColumnById(systemLog);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除日志")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public R delete(@RequestBody Long[] ids){
        systemLogService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @ApiOperation(value = "查看模块列表")
    @RequestMapping(value = "/moduleList",method = RequestMethod.GET)
    public R  moduleList(){
        List<String> list=new ArrayList<>();
        list.add("用户模块");
        list.add("角色模块");
        list.add("权限（菜单）模块");
        list.add("机构模块");
        return R.ok().put("moduleList",list);
    }

}
