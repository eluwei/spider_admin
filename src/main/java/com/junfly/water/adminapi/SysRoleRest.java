package com.junfly.water.adminapi;

import com.junfly.water.entity.sys.SRole;
import com.junfly.water.service.sys.SRoleService;
import com.junfly.water.service.sys.SRolemenuService;
import com.junfly.water.service.sys.SUserroleService;
import com.junfly.water.utils.PageUtils;
import com.junfly.water.utils.Query;
import com.junfly.water.utils.R;
import com.junfly.water.utils.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/24 19:34
 */
@RestController
@RequestMapping("/admin_api/roles")
@Api("后台角色")
public class SysRoleRest {

    @Autowired
    private SRoleService sysRoleService;

    @Autowired
    private SRolemenuService sysRoleMenuService;

    @Autowired
    private SUserroleService sysUserRoleService;

    /**
     * 角色分页列表
     */
    @PostMapping("/page")
    @ApiOperation("角色分页列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R pageList(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        List<SRole> list = sysRoleService.queryList(query);
        int total = sysRoleService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 角色列表
     */
    @GetMapping("/list")
    @ApiOperation("角色列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R list() {
        List<SRole> list = sysRoleService.queryList(new HashMap<String,Object>());
        return R.ok().put("list", list);
    }

    /**
     * 保存角色
     */
    @PostMapping("/add")
    @ApiOperation("保存角色")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R save(@RequestBody SRole sysRoleEntity) {
        ValidatorUtils.validateEntity(sysRoleEntity);
        SRole exist = sysRoleService.queryObject(sysRoleEntity.getRolecode());
        if(exist != null) {
           return R.error("角色编码已存在");
        }
        sysRoleService.save(sysRoleEntity);
        return R.ok();
    }

    /**
     * 查询角色菜单
     */
    @GetMapping("/getRoleMenu")
    @ApiOperation("查询角色菜单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R getRoleMenu(@RequestParam String rolecode) {
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(rolecode);
        return R.ok().put("menuCodeList", menuIdList);
    }

    /**
     * 查询用户角色列表
     */
    @GetMapping("/getUserRole")
    @ApiOperation("查询用户角色列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R getUserRole(@RequestParam String userId) {
        List<String> userRoleCodes = sysUserRoleService.queryRoleCodes(userId);
        return R.ok().put("userRoleCodes", userRoleCodes);
    }

    /**
     * 修改角色
     */
    @PutMapping("/edit")
    @ApiOperation("修改角色")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R update(@RequestBody SRole sysRoleEntity) {
        ValidatorUtils.validateEntity(sysRoleEntity);
        sysRoleService.update(sysRoleEntity);
        return R.ok();
    }

    /**
     * 批量删除角色
     */
    @DeleteMapping("/del")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R batchDelType(@RequestParam String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArray = ids.split(",");
            sysRoleService.deleteBatch(idArray);
        }
        return R.ok();
    }
}
