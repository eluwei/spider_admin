package com.junfly.water.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.junfly.water.mapper.sys.SRolemenuMapper;
import com.junfly.water.entity.sys.SRolemenu;
import com.junfly.water.service.sys.SRolemenuService;
import org.springframework.transaction.annotation.Transactional;


@Service("sRolemenuService")
public class SRolemenuServiceImpl implements SRolemenuService {
	@Autowired
	private SRolemenuMapper sRolemenuMapper;
	
	@Override
	public SRolemenu queryObject(String rolecode){
		return sRolemenuMapper.queryObject(rolecode);
	}
	
	@Override
	public List<SRolemenu> queryList(Map<String, Object> map){
		return sRolemenuMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sRolemenuMapper.queryTotal(map);
	}
	
	@Override
	public void save(SRolemenu sRolemenu){
		sRolemenuMapper.save(sRolemenu);
	}
	
	@Override
	public void update(SRolemenu sRolemenu){
		sRolemenuMapper.update(sRolemenu);
	}
	
	@Override
	public void delete(String rolecode){
		sRolemenuMapper.delete(rolecode);
	}
	
	@Override
	public void deleteBatch(String[] rolecodes){
		sRolemenuMapper.deleteBatch(rolecodes);
	}

	@Override
	public List<Long> queryMenuIdList(String roleCode) {
		return sRolemenuMapper.queryMenuIdList(roleCode);
	}

	@Override
	@Transactional
	public void saveOrUpdate(String roleCode, List<String> menuCodeList) {
		//先删除角色与菜单关系
		sRolemenuMapper.delete(roleCode);

		if(menuCodeList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		Map<String, Object> map = new HashMap<>();
		map.put("rolecode", roleCode);
		map.put("menuCodeList", menuCodeList);
		sRolemenuMapper.save(map);
	}
}
