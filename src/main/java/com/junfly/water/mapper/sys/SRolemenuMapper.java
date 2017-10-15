package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.SRolemenu;
import com.junfly.water.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 11:24:17
 */
@Mapper
public interface SRolemenuMapper extends BaseMapper<SRolemenu> {
    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(String roleCode);
}
