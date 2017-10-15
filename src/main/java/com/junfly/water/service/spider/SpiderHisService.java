package com.junfly.water.service.spider;


import com.junfly.water.entity.spider.SpiderHis;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-15 22:13:30
 */
public interface SpiderHisService {
	
	SpiderHis queryObject(Integer id);
	
	List<SpiderHis> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SpiderHis spiderHis);
	
	void update(SpiderHis spiderHis);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
