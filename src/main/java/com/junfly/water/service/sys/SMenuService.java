package com.junfly.water.service.sys;

import com.junfly.water.entity.sys.SMenu;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 10:49:45
 */
public interface SMenuService {
	
	SMenu queryObject(String menucode);
	
	List<SMenu> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SMenu sMenu);
	
	void update(SMenu sMenu);
	
	void delete(String menucode);
	
	void deleteBatch(String[] menucodes);

	/**
	 * 获取用户菜单列表
	 */
	List<SMenu> getUserMenuList(String userId);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SMenu> queryListParentId(String parentId, List<String> menuIdList);
}
