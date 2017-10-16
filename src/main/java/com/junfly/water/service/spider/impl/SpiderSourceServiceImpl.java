package com.junfly.water.service.spider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.junfly.water.mapper.spider.SpiderSourceMapper;
import com.junfly.water.entity.spider.SpiderSource;
import com.junfly.water.service.spider.SpiderSourceService;



@Service("spiderSourceService")
public class SpiderSourceServiceImpl implements SpiderSourceService {
	@Autowired
	private SpiderSourceMapper spiderSourceMapper;
	
	@Override
	public SpiderSource queryObject(Integer id){
		return spiderSourceMapper.queryObject(id);
	}
	
	@Override
	public List<SpiderSource> queryList(Map<String, Object> map){
		return spiderSourceMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return spiderSourceMapper.queryTotal(map);
	}
	
	@Override
	public void save(SpiderSource spiderSource){
		spiderSourceMapper.save(spiderSource);
	}
	
	@Override
	public void update(SpiderSource spiderSource){
		spiderSourceMapper.update(spiderSource);
	}
	
	@Override
	public void delete(Integer id){
		spiderSourceMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		spiderSourceMapper.deleteBatch(ids);
	}
	
}
