package com.junfly.water.service.sys;

import com.junfly.water.entity.sys.SUserrole;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pq
 * @email pq27120@126.com
 * @date 2017-06-14 11:30:59
 */
public interface SUserroleService {
	
	SUserrole queryObject(String userid);
	
	List<SUserrole> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SUserrole sUserrole);
	
	void update(SUserrole sUserrole);
	
	void delete(String userid);
	
	void deleteBatch(String[] userids);

	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleCodes(String userId);

	void saveOrUpdate(String userId, List<String> roleCodes);
}
