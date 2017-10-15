package com.junfly.water.adminapi.common;

import com.junfly.water.entity.sys.SCity;
import com.junfly.water.entity.vo.common.CascaderSelectVO;
import com.junfly.water.service.sys.SCityService;
import com.junfly.water.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/26 16:47
 */
@RestController
@RequestMapping("/admin_api/cities")
@Api("区域信息")
public class CityRest {

    private static List<CascaderSelectVO> cityEntityList = new ArrayList<>();

    @Autowired
    private SCityService sCityService;

    @GetMapping("getCityByPId")
    @ApiOperation(value = "查询区域信息", notes = "根据父节点查询区域信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R getCityByPId(@RequestParam("pId") String pId) {
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", pId);
        List<SCity> cityList = sCityService.queryList(map);
        if (cityList.isEmpty()) {
            return R.ok().put("cityList", "");
        }
        return R.ok().put("cityList", cityList);
    }

    @GetMapping("getAllCity")
    @ApiOperation(value = "查询所有区域信息", notes = "查询所有区域信息,目前取到乡镇级别")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true)
    public R getAllCity() {
        if (cityEntityList != null && !cityEntityList.isEmpty()) {
            return R.ok().put("cityList", cityEntityList);
        }
        Map<String, Object> map = new HashMap<>();
        List<SCity> cityList = sCityService.queryList(map);
        for (SCity sCityEntity : cityList) {
            if (BigDecimal.valueOf(1).compareTo(sCityEntity.getParentId()) == 0 &&
                    BigDecimal.valueOf(1).compareTo(sCityEntity.getRegionType()) == 0) {
                CascaderSelectVO cascaderSelectVO = new CascaderSelectVO();
                cascaderSelectVO.setValue(String.valueOf(sCityEntity.getRegionId()));
                cascaderSelectVO.setLabel(sCityEntity.getRegionName());
                cascaderSelectVO.setType(String.valueOf(sCityEntity.getRegionType()));
                cityEntityList.add(cascaderSelectVO);
            }
        }
        getChildCityList(cityEntityList, cityList);
        return R.ok().put("cityList", cityEntityList);
    }

    /**
     * 获取子机构
     *
     * @param pCityList
     * @param allCityList
     */
    private void getChildCityList(List<CascaderSelectVO> pCityList, List<SCity> allCityList) {
        for (CascaderSelectVO sCityEntity : pCityList) {
            List<CascaderSelectVO> childList = new ArrayList<>();
            for (SCity cityEntity : allCityList) {
                if (sCityEntity.getValue().equals(String.valueOf(cityEntity.getParentId()))) {
                    CascaderSelectVO cascaderSelectVO = new CascaderSelectVO();
                    cascaderSelectVO.setValue(String.valueOf(cityEntity.getRegionId()));
                    cascaderSelectVO.setLabel(cityEntity.getRegionName());
                    cascaderSelectVO.setType(String.valueOf(cityEntity.getRegionType()));
                    childList.add(cascaderSelectVO);
                }
            }
            if (childList != null && !childList.isEmpty()) {
                sCityEntity.setChildren(childList);
                getChildCityList(childList, allCityList);
            } else {
                sCityEntity.setChildren(null);
            }
        }
    }
}
