package com.junfly.water.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.junfly.water.mapper.sys.SUserroleMapper;
import com.junfly.water.entity.sys.SUserrole;
import com.junfly.water.service.sys.SUserroleService;



@Service("sUserroleService")
public class SUserroleServiceImpl implements SUserroleService {
	@Autowired
	private SUserroleMapper sUserroleMapper;
	
	@Override
	public SUserrole queryObject(String userid){
		return sUserroleMapper.queryObject(userid);
	}
	
	@Override
	public List<SUserrole> queryList(Map<String, Object> map){
		return sUserroleMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sUserroleMapper.queryTotal(map);
	}
	
	@Override
	public void save(SUserrole sUserrole){
		sUserroleMapper.save(sUserrole);
	}
	
	@Override
	public void update(SUserrole sUserrole){
		sUserroleMapper.update(sUserrole);
	}
	
	@Override
	public void delete(String userid){
		sUserroleMapper.delete(userid);
	}
	
	@Override
	public void deleteBatch(String[] userids){
		sUserroleMapper.deleteBatch(userids);
	}

	@Override
	public List<String> queryRoleCodes(String userId) {
		return sUserroleMapper.queryRoleCodes(userId);
	}

	@Override
	public void saveOrUpdate(String userId, List<String> roleCodes) {
		if(roleCodes.size() == 0){
			return ;
		}

		//先删除用户与角色关系
		sUserroleMapper.delete(userId);

		//保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		map.put("userid", userId);
		map.put("roleCodes", roleCodes);
		sUserroleMapper.save(map);
	}
}
