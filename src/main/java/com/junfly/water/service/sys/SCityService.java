package com.junfly.water.service.sys;

import com.junfly.water.entity.sys.SCity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-26 16:42:35
 */
public interface SCityService {
	
	SCity queryObject(BigDecimal regionId);
	
	List<SCity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SCity sCity);
	
	void update(SCity sCity);
	
	void delete(BigDecimal regionId);
	
	void deleteBatch(BigDecimal[] regionIds);
}
