package com.junfly.water.service.spider;


import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.entity.spider.SpiderHis;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-10-14 21:24:07
 */
public interface PybbsTopicService {
	
	PybbsTopic queryObject(Integer id);
	
	List<PybbsTopic> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PybbsTopic pybbsTopic);
	
	void update(PybbsTopic pybbsTopic);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void saveWithHistory(PybbsTopic pybbsTopic, String channel);

	void updateWithHistory(PybbsTopic pybbsTopic);
}
