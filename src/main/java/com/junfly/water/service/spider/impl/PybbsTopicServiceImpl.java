package com.junfly.water.service.spider.impl;

import com.junfly.water.entity.spider.SpiderHis;
import com.junfly.water.mapper.spider.SpiderHisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


import com.junfly.water.mapper.spider.PybbsTopicMapper;
import com.junfly.water.entity.spider.PybbsTopic;
import com.junfly.water.service.spider.PybbsTopicService;
import org.springframework.transaction.annotation.Transactional;


@Service("pybbsTopicService")
public class PybbsTopicServiceImpl implements PybbsTopicService {

	private static Logger logger = LoggerFactory.getLogger(PybbsTopicServiceImpl.class);
	@Autowired
	private PybbsTopicMapper pybbsTopicMapper;

	@Autowired
	private SpiderHisMapper spiderHisMapper;
	
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

	@Override
	@Transactional
	public void saveWithHistory(PybbsTopic pybbsTopic, String channel) {
		try {
			SpiderHis spiderHis = new SpiderHis();
			spiderHis.setHisTitle(pybbsTopic.getTitle());
			pybbsTopicMapper.save(pybbsTopic);
			spiderHis.setId(pybbsTopic.getId());
			spiderHis.setImageProcess("1");
			spiderHis.setChannel(channel);
			spiderHisMapper.save(spiderHis);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.info("标题== " + pybbsTopic.getTitle());
			logger.info("内容== " + pybbsTopic.getContent());
		}
	}

	@Override
	@Transactional
	public void updateWithHistory(PybbsTopic pybbsTopic) {
		SpiderHis spiderHis = new SpiderHis();
		pybbsTopicMapper.update(pybbsTopic);
		spiderHis.setId(pybbsTopic.getId());
		spiderHis.setImageProcess("3");
		spiderHisMapper.update(spiderHis);
	}

}
