package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper{

	/**
	 * 查询符合条件的所有记录
	 * @param city
	 * @return List<City>
	 */
	@SuppressWarnings("unchecked")
	List<City> cityList(City city);
}
