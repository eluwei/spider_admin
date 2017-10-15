package com.junfly.water.mapper;

import com.junfly.water.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper{
	/**
	 * 查询登录用户
	 * @param user
	 * @return
	 */
	User userLoginUser(User user);
}
