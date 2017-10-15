package com.junfly.water.service.sys.impl;

import com.junfly.water.entity.sys.SCity;
import com.junfly.water.mapper.sys.SCityMapper;
import com.junfly.water.service.sys.SCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service("sCityService")
public class SCityServiceImpl implements SCityService {
	@Autowired
	private SCityMapper sCityDao;
	
	@Override
	public SCity queryObject(BigDecimal regionId){
		return sCityDao.queryObject(regionId);
	}
	
	@Override
	public List<SCity> queryList(Map<String, Object> map){
		return sCityDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sCityDao.queryTotal(map);
	}
	
	@Override
	public void save(SCity sCity){
		sCityDao.save(sCity);
	}
	
	@Override
	public void update(SCity sCity){
		sCityDao.update(sCity);
	}
	
	@Override
	public void delete(BigDecimal regionId){
		sCityDao.delete(regionId);
	}
	
	@Override
	public void deleteBatch(BigDecimal[] regionIds){
		sCityDao.deleteBatch(regionIds);
	}
	
}
