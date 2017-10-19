package com.junfly.water.service.spider;


import com.junfly.water.entity.spider.SpiderProcess;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-19 21:37:49
 */
public interface SpiderProcessService {
	
	SpiderProcess queryObject(Integer id);
	
	List<SpiderProcess> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SpiderProcess spiderProcess);
	
	void update(SpiderProcess spiderProcess);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
