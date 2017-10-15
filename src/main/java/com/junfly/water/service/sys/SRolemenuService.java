package com.junfly.water.service.sys;

import com.junfly.water.entity.sys.SRolemenu;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 11:24:17
 */
public interface SRolemenuService {
	
	SRolemenu queryObject(String rolecode);
	
	List<SRolemenu> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SRolemenu sRolemenu);
	
	void update(SRolemenu sRolemenu);
	
	void delete(String rolecode);
	
	void deleteBatch(String[] rolecodes);

	/**
	 * 根据角色，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(String roleCode);

	void saveOrUpdate(String roleCode, List<String> menuCodeList);
}
