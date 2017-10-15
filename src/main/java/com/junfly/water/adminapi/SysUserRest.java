package com.junfly.water.adminapi;

import com.junfly.water.entity.sys.User;
import com.junfly.water.entity.vo.sys.PassChangeVO;
import com.junfly.water.service.sys.SysUserService;
import com.junfly.water.utils.*;
import com.junfly.water.utils.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.junfly.water.config.Global.LOGIN_USER_KEY;


/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/26 15:43
 */
@RestController
@RequestMapping("/admin_api/users")
@Api("用户信息")
public class SysUserRest {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户分页列表
     */
    @PostMapping("/page")
    @ApiOperation("用户分页列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R pageTypeList(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        List<User> list = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 保存用户
     */
    @PostMapping("/add")
    @ApiOperation("保存用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R save(@RequestBody User userEntity) {
        ValidatorUtils.validateEntity(userEntity);
        if (StringUtils.isEmpty(userEntity.getPassword())) {
            userEntity.setPassword("123456");
        }
        userEntity.setPassword(Md5Encrypt.md5(userEntity.getPassword()));
        User result = sysUserService.queryByUserCode(userEntity.getUsercode());
        if (result != null) {
            return R.error("账号已存在");
        }
        if (StringUtils.isNotEmpty(userEntity.getOrgcodes())) {
            String[] parentArray = userEntity.getOrgcodes().split(",");
            userEntity.setOrgcode(parentArray[parentArray.length - 1]);
        }
        userEntity.setUserid(new GuidCreator().createNewGuid(GuidCreator.AfterMD5));
        userEntity.setIsactive("1");
        userEntity.setIsdelete("0");
        sysUserService.save(userEntity);
        return R.ok();
    }

    /**
     * 修改用户
     */
    @PutMapping("/edit")
    @ApiOperation("修改用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R update(@RequestBody User userEntity) {
        ValidatorUtils.validateEntity(userEntity);
        sysUserService.update(userEntity);
        return R.ok();
    }

    /**
     * 密码重置
     */
    @PostMapping("/passReset")
    @ApiOperation("密码重置")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R passReset(@RequestBody PassChangeVO passChangeVO) {
        ValidatorUtils.validateEntity(passChangeVO);
        if (!passChangeVO.getNewPass().equals(passChangeVO.getNewPassAgain())) {
            return R.error("两次输入的密码不一致！");
        }
        passChangeVO.setNewPass(Md5Encrypt.md5(passChangeVO.getNewPass()));
        sysUserService.updatePassword(passChangeVO.getUserid(), passChangeVO.getNewPass());
        return R.ok();
    }

    /**
     * 密码修改
     */
    @PostMapping("/passEdit")
    @ApiOperation("密码修改")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R passEdit(HttpServletRequest request, @RequestBody PassChangeVO passChangeVO) {
        ValidatorUtils.validateEntity(passChangeVO);
        if (!passChangeVO.getNewPass().equals(passChangeVO.getNewPassAgain())) {
            return R.error("两次输入的密码不一致！");
        }
        String userId = (String) request.getAttribute(LOGIN_USER_KEY);
        User entity = sysUserService.queryObject(userId);
        if (entity == null) {
            return R.error("用户不存在！");
        }
        if (!entity.getPassword().equals(Md5Encrypt.md5(passChangeVO.getOldPass()))) {
            return R.error("密码错误！");
        }
        passChangeVO.setNewPass(Md5Encrypt.md5(passChangeVO.getNewPass()));
        sysUserService.updatePassword(userId, passChangeVO.getNewPass());
        return R.ok();
    }

    /**
     * 批量禁用用户
     */
    @DeleteMapping("/del")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R batchDelType(@RequestParam("ids") String ids, @RequestParam("type") String type) {
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArray = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                User User = new User();
                User.setUserid(idArray[i]);
                User.setIsactive(type);
                sysUserService.updateUser(User);
            }
        }
        return R.ok();
    }
}
