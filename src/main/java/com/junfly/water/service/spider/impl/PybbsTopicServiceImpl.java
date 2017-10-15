package com.junfly.water.service.spider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.junfly.water.mapper.spider.PybbsTopicMapper;
import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.service.spider.PybbsTopicService;



@Service("pybbsTopicService")
public class PybbsTopicServiceImpl implements PybbsTopicService {
	@Autowired
	private PybbsTopicMapper pybbsTopicMapper;
	
	@Override
	public PybbsTopic queryObject(Integer id){
		return pybbsTopicMapper.queryObject(id);
	}
	
	@Override
	public List<PybbsTopic> queryList(Map<String, Object> map){
		return pybbsTopicMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return pybbsTopicMapper.queryTotal(map);
	}
	
	@Override
	public void save(PybbsTopic pybbsTopic){
		pybbsTopicMapper.save(pybbsTopic);
	}
	
	@Override
	public void update(PybbsTopic pybbsTopic){
		pybbsTopicMapper.update(pybbsTopic);
	}
	
	@Override
	public void delete(Integer id){
		pybbsTopicMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		pybbsTopicMapper.deleteBatch(ids);
	}
	
}
