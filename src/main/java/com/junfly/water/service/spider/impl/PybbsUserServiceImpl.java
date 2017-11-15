package com.junfly.water.service.spider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.junfly.water.mapper.spider.PybbsUserMapper;
import com.junfly.water.entity.spider.PybbsUser;
import com.junfly.water.service.spider.PybbsUserService;



@Service("pybbsUserService")
public class PybbsUserServiceImpl implements PybbsUserService {
	@Autowired
	private PybbsUserMapper pybbsUserMapper;
	
	@Override
	public PybbsUser queryObject(Integer id){
		return pybbsUserMapper.queryObject(id);
	}

	@Override
	public PybbsUser queryObjectByRand() {
		return pybbsUserMapper.queryObjectByRand();
	}

	@Override
	public List<PybbsUser> queryList(Map<String, Object> map){
		return pybbsUserMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return pybbsUserMapper.queryTotal(map);
	}
	
	@Override
	public void save(PybbsUser pybbsUser){
		pybbsUserMapper.save(pybbsUser);
	}
	
	@Override
	public void update(PybbsUser pybbsUser){
		pybbsUserMapper.update(pybbsUser);
	}
	
	@Override
	public void delete(Integer id){
		pybbsUserMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		pybbsUserMapper.deleteBatch(ids);
	}
	
}
