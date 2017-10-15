package com.junfly.water.adminapi;

import com.junfly.water.entity.sys.SMenu;
import com.junfly.water.entity.vo.common.CascaderSelectVO;
import com.junfly.water.entity.vo.common.TreeVO;
import com.junfly.water.service.sys.SMenuService;
import com.junfly.water.utils.GuidCreator;
import com.junfly.water.utils.PageUtils;
import com.junfly.water.utils.Query;
import com.junfly.water.utils.R;
import com.junfly.water.utils.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.junfly.water.config.Global.LOGIN_USER_KEY;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/24 19:34
 */
@RestController
@RequestMapping("/admin_api/menus")
@Api("后台菜单")
public class SysMenuRest {

    private Logger logger = LoggerFactory.getLogger(SysMenuRest.class);
//    private static List<SysMenuEntity> sysMenuEntities = new ArrayList<>();

    @Autowired
    private SMenuService sysMenuService;

    @Value("${image.filePath}")
    private String filePath;

    /**
     * 菜单分页列表
     */
    @PostMapping("/page")
    @ApiOperation("菜单分页列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R pageList(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        List<SMenu> list = sysMenuService.queryList(query);
        int total = sysMenuService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    @GetMapping("getAllMenu")
    @ApiOperation(value = "查询所有菜单信息", notes = "查询所有菜单信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R getAllMenu() {
        List<CascaderSelectVO> selectVOS = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<SMenu> menuList = sysMenuService.queryList(map);
        for (SMenu sysMenuEntity : menuList) {
            if ("0".equals(sysMenuEntity.getPmenucode())) {
                CascaderSelectVO cascaderSelectVO = new CascaderSelectVO();
                cascaderSelectVO.setValue(String.valueOf(sysMenuEntity.getMenucode()));
                cascaderSelectVO.setLabel(sysMenuEntity.getMenuname());
                selectVOS.add(cascaderSelectVO);
            }
        }
        getChildMenuList(selectVOS, menuList);
        return R.ok().put("menuList", selectVOS);
    }

    @GetMapping("getTreeMenu")
    @ApiOperation(value = "查询菜单树", notes = "查询菜单树")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R getTreeMenu() {
        List<TreeVO> trees = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<SMenu> menuList = sysMenuService.queryList(map);
        for (SMenu sysMenuEntity : menuList) {
            if ("0".equals(sysMenuEntity.getPmenucode())) {
                TreeVO treeVO = new TreeVO();
                treeVO.setId(String.valueOf(sysMenuEntity.getMenucode()));
                treeVO.setLabel(sysMenuEntity.getMenuname());
                trees.add(treeVO);
            }
        }
        getChildMenuTree(trees, menuList);
        return R.ok().put("menuTree", trees);
    }

    private void getChildMenuTree(List<TreeVO> trees, List<SMenu> menuList) {
        for (TreeVO treeVO : trees) {
            List<TreeVO> childList = new ArrayList<>();
            for (SMenu menuEntity : menuList) {
                if (treeVO.getId().equals(menuEntity.getPmenucode())) {
                    TreeVO treeVO1 = new TreeVO();
                    treeVO1.setId(menuEntity.getMenucode());
                    treeVO1.setLabel(menuEntity.getMenuname());
                    childList.add(treeVO1);
                }
            }
            if (childList != null && !childList.isEmpty()) {
                treeVO.setChildren(childList);
                getChildMenuTree(childList, menuList);
            } else {
                treeVO.setChildren(null);
            }
        }
    }

    /**
     * 保存菜单
     */
    @PostMapping("/add")
    @ApiOperation("保存菜单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R save(@RequestBody SMenu sysMenuEntity) {
        ValidatorUtils.validateEntity(sysMenuEntity);
        if (StringUtils.isNotEmpty(sysMenuEntity.getPMenuCodes())) {
            String[] parentArray = sysMenuEntity.getPMenuCodes().split(",");
            sysMenuEntity.setPmenucode(parentArray[parentArray.length - 1]);
        } else {
            sysMenuEntity.setPmenucode("0");
            sysMenuEntity.setPMenuCodes("0");
        }
        sysMenuEntity.setMenucode(new GuidCreator().createNewGuid(GuidCreator.AfterMD5));
        sysMenuEntity.setIsdelete("0");
        sysMenuService.save(sysMenuEntity);
        return R.ok();
    }

    /**
     * 修改菜单
     */
    @PutMapping("/edit")
    @ApiOperation("修改菜单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R update(@RequestBody SMenu sysMenuEntity) {
        ValidatorUtils.validateEntity(sysMenuEntity);
        if (StringUtils.isNotEmpty(sysMenuEntity.getPMenuCodes())) {
            String[] parentArray = sysMenuEntity.getPMenuCodes().split(",");
            sysMenuEntity.setPmenucode(parentArray[parentArray.length - 1]);
        }
        sysMenuService.update(sysMenuEntity);
        return R.ok();
    }

    /**
     * 批量删除菜单
     */
    @DeleteMapping("/del")
    @ApiOperation("批量删除菜单")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R batchDelType(@RequestParam String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArray = ids.split(",");
            sysMenuService.deleteBatch(idArray);
        }
        return R.ok();
    }

    /**
     * 递归获取子菜单
     *
     * @param sysMenuEntities
     * @param menuList
     */
    private void getChildMenuList(List<CascaderSelectVO> sysMenuEntities, List<SMenu> menuList) {
        for (CascaderSelectVO selectVO : sysMenuEntities) {
            List<CascaderSelectVO> childList = new ArrayList<>();
            for (SMenu menuEntity : menuList) {
                if (selectVO.getValue().equals(String.valueOf(menuEntity.getPmenucode()))) {
                    CascaderSelectVO cascaderSelectVO = new CascaderSelectVO();
                    cascaderSelectVO.setValue(String.valueOf(menuEntity.getMenucode()));
                    cascaderSelectVO.setLabel(menuEntity.getMenuname());
                    childList.add(cascaderSelectVO);
                }
            }
            if (!"2".equals(selectVO.getType()) && childList != null && !childList.isEmpty()) {
                getChildMenuList(childList, menuList);
            }
            if (childList != null && !childList.isEmpty()) {
                selectVO.setChildren(childList);
            } else {
                selectVO.setChildren(null);
            }
        }
    }

    /**
     * 菜单图片上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadImg")
    @ApiOperation("图片上传")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R uploadImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.error("文件为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = "menu/" + UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return R.ok().put("imgPath", fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("上传失败");
    }

    /**
     * 用户菜单列表
     */
    @GetMapping("/userMenuList")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R userMenuList(HttpServletRequest request) {
        String userId = (String) request.getAttribute(LOGIN_USER_KEY);
        List<SMenu> menuList = sysMenuService.getUserMenuList(userId);
        return R.ok().put("menuList", menuList);
    }
}
