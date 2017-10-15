package com.junfly.water.service.sys.impl;

import com.junfly.water.mapper.sys.SRolemenuMapper;
import com.junfly.water.service.sys.SRolemenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.junfly.water.mapper.sys.SRoleMapper;
import com.junfly.water.entity.sys.SRole;
import com.junfly.water.service.sys.SRoleService;
import org.springframework.transaction.annotation.Transactional;


@Service("sRoleService")
public class SRoleServiceImpl implements SRoleService {
	@Autowired
	private SRoleMapper sRoleMapper;

	@Autowired
	private SRolemenuService sRolemenuService;
	
	@Override
	public SRole queryObject(String rolecode){
		return sRoleMapper.queryObject(rolecode);
	}
	
	@Override
	public List<SRole> queryList(Map<String, Object> map){
		return sRoleMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sRoleMapper.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(SRole sRole) {
		sRoleMapper.save(sRole);

		//保存角色与菜单关系
		sRolemenuService.saveOrUpdate(sRole.getRolecode(), sRole.getMenuCodeList());
	}


	@Override
	@Transactional
	public void update(SRole sRole) {
		sRoleMapper.update(sRole);

		//更新角色与菜单关系
		sRolemenuService.saveOrUpdate(sRole.getRolecode(), sRole.getMenuCodeList());
	}
	
	@Override
	public void delete(String rolecode){
		sRoleMapper.delete(rolecode);
	}
	
	@Override
	@Transactional
	public void deleteBatch(String[] rolecodes){
		sRoleMapper.deleteBatch(rolecodes);
	}
	
}
