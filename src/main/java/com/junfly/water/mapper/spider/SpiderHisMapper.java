package com.junfly.water.mapper.spider;


import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-15 22:13:30
 */
@Mapper
public interface SpiderHisMapper extends BaseMapper<SpiderHis> {

    List<SpiderHis> queryByTitle(SpiderHis spiderHis);
}
