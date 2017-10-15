package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.User;
import com.junfly.water.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
@Mapper
public interface SysUserMapper extends BaseMapper<User> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	User queryByUserCode(String username);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);

	/**
	 * 根据条件，查询系统用户
	 * @param user
	 * @return
	 */
	User queryByCond(User user);

}
