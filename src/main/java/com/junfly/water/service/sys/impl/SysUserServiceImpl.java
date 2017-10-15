package com.junfly.water.service.sys.impl;

import com.junfly.water.entity.sys.User;
import com.junfly.water.mapper.sys.SysUserMapper;
import com.junfly.water.service.sys.SRoleService;
import com.junfly.water.service.sys.SUserroleService;
import com.junfly.water.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SUserroleService sysUserRoleService;
	@Autowired
	private SRoleService sysRoleService;

	@Override
	public List<String> queryAllPerms(Long userId) {
		return sysUserMapper.queryAllPerms(userId);
	}

	@Override
	public List<String> queryAllMenuId(String userId) {
		return sysUserMapper.queryAllMenuId(userId);
	}

	@Override
	public User queryByUserCode(String usercode) {
		return sysUserMapper.queryByUserCode(usercode);
	}

	@Override
	public User queryByCond(User user){
		return sysUserMapper.queryByCond(user);
	}
	
	@Override
	public User queryObject(String userId) {
		return sysUserMapper.queryObject(userId);
	}

	@Override
	public List<User> queryList(Map<String, Object> map){
		return sysUserMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserMapper.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(User user) {
		sysUserMapper.save(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserid(), user.getRoleCodes());
	}



	@Override
	@Transactional
	public void update(User user) {
		sysUserMapper.update(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserid(), user.getRoleCodes());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] userId) {
		sysUserMapper.deleteBatch(userId);
	}

	@Override
	public int updatePassword(String userId, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userid", userId);
		map.put("newPassword", newPassword);
		return sysUserMapper.updatePassword(map);
	}

	@Override
	public void updateUser(User user) {
		sysUserMapper.update(user);
	}
}
