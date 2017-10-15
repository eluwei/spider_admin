package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.SUserrole;
import com.junfly.water.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 11:30:59
 */
@Mapper
public interface SUserroleMapper extends BaseMapper<SUserrole> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<String> queryRoleCodes(String userId);
}
