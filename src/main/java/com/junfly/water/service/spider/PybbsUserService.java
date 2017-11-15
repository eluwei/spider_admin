package com.junfly.water.service.spider;


import com.junfly.water.entity.spider.PybbsUser;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-11-15 22:34:54
 */
public interface PybbsUserService {
	
	PybbsUser queryObject(Integer id);

	PybbsUser queryObjectByRand();

	List<PybbsUser> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PybbsUser pybbsUser);
	
	void update(PybbsUser pybbsUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
