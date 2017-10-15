package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.Userrole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserroleMapper {
	/**
	 * 查询符合条件的所有记录
	 * @param userrole
	 * @return List<Userrole>
	 */
	@SuppressWarnings("unchecked")
	List<Userrole> userroleList(Userrole userrole);
}
