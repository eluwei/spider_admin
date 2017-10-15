package com.junfly.water.adminapi;

import com.junfly.water.config.Global;
import com.junfly.water.entity.sys.User;
import com.junfly.water.entity.vo.sys.SysUserVO;
import com.junfly.water.service.sys.SUserroleService;
import com.junfly.water.service.sys.SysTokenService;
import com.junfly.water.service.sys.SysUserService;
import com.junfly.water.utils.Md5Encrypt;
import com.junfly.water.utils.R;
import com.junfly.water.utils.RRException;
import com.junfly.water.utils.annotation.IgnoreAuth;
import com.junfly.water.utils.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/24 16:55
 */
@RestController
@RequestMapping("/admin_api")
@Api("后台登录")
public class SysLoginRest {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysTokenService sysTokenService;
    @Autowired
    private SUserroleService sUserroleService;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "登录", notes = "登录")
    public R login(@RequestBody SysUserVO sysUserVO) {
        Assert.isBlank(sysUserVO.getUsername(), "用户名不能为空");
        Assert.isBlank(sysUserVO.getPassword(), "密码不能为空");

        //用户登录
        User paramEntity = new User();
        paramEntity.setUsercode(sysUserVO.getUsername());
        User resultUser = sysUserService.queryByCond(paramEntity);
        Assert.isNull(resultUser, "用户名或密码错误");

        //密码错误
        if (!resultUser.getPassword().equals(Md5Encrypt.md5(sysUserVO.getPassword()))) {
            throw new RRException("用户名或密码错误");
        }

        //生成token
        Map<String, Object> map = sysTokenService.createToken(resultUser.getUserid());

        return R.ok(map);
    }

    @GetMapping("getUserInfo")
    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R getUserInfo(HttpServletRequest request) {
        String userId = (String)request.getAttribute(Global.LOGIN_USER_KEY);
        User resultUser = sysUserService.queryObject(userId);

        List<String> roleCodes = sUserroleService.queryRoleCodes(userId);

        return R.ok().put("user", resultUser).put("roleCodes", roleCodes);
    }
}
