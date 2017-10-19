package com.junfly.water.service.spider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.junfly.water.mapper.spider.SpiderProcessMapper;
import com.junfly.water.entity.spider.SpiderProcess;
import com.junfly.water.service.spider.SpiderProcessService;



@Service("spiderProcessService")
public class SpiderProcessServiceImpl implements SpiderProcessService {
	@Autowired
	private SpiderProcessMapper spiderProcessMapper;
	
	@Override
	public SpiderProcess queryObject(Integer id){
		return spiderProcessMapper.queryObject(id);
	}
	
	@Override
	public List<SpiderProcess> queryList(Map<String, Object> map){
		return spiderProcessMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return spiderProcessMapper.queryTotal(map);
	}
	
	@Override
	public void save(SpiderProcess spiderProcess){
		spiderProcessMapper.save(spiderProcess);
	}
	
	@Override
	public void update(SpiderProcess spiderProcess){
		spiderProcessMapper.update(spiderProcess);
	}
	
	@Override
	public void delete(Integer id){
		spiderProcessMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		spiderProcessMapper.deleteBatch(ids);
	}
	
}
