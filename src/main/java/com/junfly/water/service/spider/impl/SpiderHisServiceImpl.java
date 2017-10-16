package com.junfly.water.service.spider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.junfly.water.mapper.spider.SpiderHisMapper;
import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.service.spider.SpiderHisService;



@Service("spiderHisService")
public class SpiderHisServiceImpl implements SpiderHisService {
	@Autowired
	private SpiderHisMapper spiderHisMapper;
	
	@Override
	public SpiderHis queryObject(Integer id){
		return spiderHisMapper.queryObject(id);
	}
	
	@Override
	public List<SpiderHis> queryList(Map<String, Object> map){
		return spiderHisMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return spiderHisMapper.queryTotal(map);
	}
	
	@Override
	public void save(SpiderHis spiderHis){
		spiderHisMapper.save(spiderHis);
	}
	
	@Override
	public void update(SpiderHis spiderHis){
		spiderHisMapper.update(spiderHis);
	}
	
	@Override
	public void delete(Integer id){
		spiderHisMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		spiderHisMapper.deleteBatch(ids);
	}

	@Override
	public List<SpiderHis> queryByTitle(String title) {
		SpiderHis spiderHis = new SpiderHis();
		spiderHis.setHisTitle(title);
		return spiderHisMapper.queryByTitle(spiderHis);
	}

}
