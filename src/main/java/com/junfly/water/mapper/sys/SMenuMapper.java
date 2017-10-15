package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.SMenu;
import com.junfly.water.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 10:49:45
 */
@Mapper
public interface SMenuMapper extends BaseMapper<SMenu> {

    /**
     * 根据父菜单，查询子菜单
     * @param pmenucode 父菜单ID
     */
    List<SMenu> queryListParentId(@Param("pmenucode") String pmenucode);
}
