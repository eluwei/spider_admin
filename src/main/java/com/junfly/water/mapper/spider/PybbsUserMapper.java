package com.junfly.water.mapper.spider;


import com.junfly.water.entity.spider.PybbsUser;
import com.junfly.water.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-11-15 22:34:54
 */
@Mapper
public interface PybbsUserMapper extends BaseMapper<PybbsUser> {

    PybbsUser queryObjectByRand();

}
