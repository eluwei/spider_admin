package com.junfly.water.service.spider;


import com.junfly.water.entity.spider.SpiderSource;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-16 21:03:58
 */
public interface SpiderSourceService {
	
	SpiderSource queryObject(Integer id);
	
	List<SpiderSource> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SpiderSource spiderSource);
	
	void update(SpiderSource spiderSource);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
