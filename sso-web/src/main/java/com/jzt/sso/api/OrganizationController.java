package com.jzt.sso.api;


import com.jzt.sso.annotation.SysLog;

import com.jzt.sso.dto.OrgDTO;
import com.jzt.sso.model.OrganizationEntity;
import com.jzt.sso.service.OrganizationService;
import com.jzt.sso.service.UserService;
import com.jzt.sso.utils.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Api(tags = "机构管理模块")
@RestController
@RequestMapping("sys/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;
    /**
     * 列表
     */
    @ApiOperation(value = "获取机构列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "页数量", dataType = "Integer", paramType = "query")})
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @SysLog(value = "查询机构列表",moduleName = "机构模块")
    public R list(@RequestBody OrgDTO dto) {
        if(dto.getLimit()==null){
            dto.setLimit(10);
        }
        String username=  UserUtil.getUserName();
        Long userId=userService.selectByName(username).getId();
        dto.setUserId(userId);
        Map<String, Object> params = new HashMap<>();
        params.put("page", dto.getPage().toString());
        params.put("limit", dto.getLimit().toString());
        PageUtils page = organizationService.queryPage(params, dto);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查看机构信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @SysLog(value = "查看机构信息",moduleName = "机构模块")
    public R info(@PathVariable("id") Long id) {
        OrganizationEntity organization = organizationService.selectById(id);
        return R.ok().put("organization", organization);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增机构")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @SysLog(value = "新增机构",moduleName = "机构模块")
    @Transactional
    public R save(@RequestBody @Valid OrganizationEntity organization) {
        Boolean phone = ValidataUtil.isPhoneNumber(organization.getConcatPhone());
        if (phone == false) {
            return R.error("手机号不合法。");
        }
        List<String> orgNames=organizationService.getOrgNames(0L);
        if(orgNames.size()>0&&orgNames.contains(organization.getOrgName())){
            return R.error("机构名字已经存在。");
        }
        organizationService.insert(organization);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改机构")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    @SysLog(value = "修改机构",moduleName = "机构模块")
    public R update(@RequestBody @Valid OrganizationEntity organization) {
        Boolean phone = ValidataUtil.isPhoneNumber(organization.getConcatPhone());
        if (phone == false) {
            return R.error("输入的手机号不合法。");
        }
        List<String> orgNames=organizationService.getOrgNames(organization.getId());
        if(orgNames.size()>0 &&orgNames.contains(organization.getOrgName())){
            return R.error("机构名字已经存在。");
        }
        organizationService.updateAllColumnById(organization);//全部更新
        return R.ok();
    }

    @ApiOperation(value = "修改机构状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @Transactional
    @SysLog(value = "修改机构状态",moduleName = "机构模块")
    public R updateStatus(@RequestBody OrganizationEntity organization) {
        OrganizationEntity entity = organizationService.selectById(organization.getId());
        if(entity==null){
            return R.error("机构id不存在。");
        }
        entity.setStatus(organization.getStatus());
        organizationService.updateAllColumnById(entity);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @PreAuthorize("hasAnyAuthority('sys/organization/delete')")
    @ApiOperation(value = "删除机构")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional
    @SysLog(value = "删除机构",moduleName = "机构模块")
    public R delete(@RequestBody Long[] ids) {
        organizationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
