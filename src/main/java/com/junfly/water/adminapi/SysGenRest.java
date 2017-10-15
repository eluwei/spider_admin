package com.junfly.water.adminapi;

import com.junfly.water.service.sys.SysGeneratorService;
import com.junfly.water.utils.PageUtils;
import com.junfly.water.utils.Query;
import com.junfly.water.utils.R;
import com.junfly.water.utils.annotation.IgnoreAuth;
import com.junfly.water.utils.xss.XssHttpServletRequestWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/6/1 16:18
 */
@RestController
@RequestMapping("/admin_api/gens")
@Api("代码生成")
public class SysGenRest {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 代码生成分页列表
     */
    @PostMapping("/page")
    @ApiOperation("代码生成分页列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R pageList(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     */
    @IgnoreAuth
    @GetMapping("/code")
    @ApiOperation("代码生成")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取表名，不进行xss过滤
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String tables = orgRequest.getParameter("tables");
        String[] tableArray = tables.split(",");

        byte[] data = sysGeneratorService.generatorCode(tableArray, "");

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
