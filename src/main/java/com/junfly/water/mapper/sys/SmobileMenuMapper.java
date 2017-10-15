package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.SmobileMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmobileMenuMapper{
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return SmobileMenu
	 */
	SmobileMenu queryMobileMenuById(String id);
}
