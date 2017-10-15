package com.junfly.water.service.sys;

import com.junfly.water.entity.sys.SRole;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 10:50:24
 */
public interface SRoleService {
	
	SRole queryObject(String rolecode);
	
	List<SRole> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SRole sRole);
	
	void update(SRole sRole);
	
	void delete(String rolecode);
	
	void deleteBatch(String[] rolecodes);
}
