package com.junfly.water.service.sys.impl;

import com.junfly.water.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.junfly.water.mapper.sys.SMenuMapper;
import com.junfly.water.entity.sys.SMenu;
import com.junfly.water.service.sys.SMenuService;



@Service("sMenuService")
public class SMenuServiceImpl implements SMenuService {
	@Autowired
	private SMenuMapper sMenuMapper;

	@Autowired
	private SysUserService sysUserService;
	
	@Override
	public SMenu queryObject(String menucode){
		return sMenuMapper.queryObject(menucode);
	}
	
	@Override
	public List<SMenu> queryList(Map<String, Object> map){
		return sMenuMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sMenuMapper.queryTotal(map);
	}
	
	@Override
	public void save(SMenu sMenu){
		sMenuMapper.save(sMenu);
	}
	
	@Override
	public void update(SMenu sMenu){
		sMenuMapper.update(sMenu);
	}
	
	@Override
	public void delete(String menucode){
		sMenuMapper.delete(menucode);
	}
	
	@Override
	public void deleteBatch(String[] menucodes){
		sMenuMapper.deleteBatch(menucodes);
	}

	@Override
	public List<SMenu> getUserMenuList(String userId) {
		//系统管理员，拥有最高权限
		if("1000000000".equals(userId)){
			return getAllMenuList(null);
		}

		//用户菜单列表
		List<String> menuCodeList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuCodeList);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SMenu> getAllMenuList(List<String> menuCodeList){
		//查询根菜单列表
		List<SMenu> menuList = queryListParentId("0", menuCodeList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuCodeList);

		return menuList;
	}

	@Override
	public List<SMenu> queryListParentId(String parentId, List<String> menuCodeList) {
		List<SMenu> menuList = sMenuMapper.queryListParentId(parentId);
		if(menuCodeList == null){
			return menuList;
		}

		List<SMenu> userMenuList = new ArrayList<>();
		for(SMenu menu : menuList){
			if(menuCodeList.contains(menu.getMenucode())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	/**
	 * 递归
	 */
	private List<SMenu> getMenuTreeList(List<SMenu> menuList, List<String> menuCodeList){
		List<SMenu> subMenuList = new ArrayList<>();

		for(SMenu entity : menuList){
			List<SMenu> childList = getMenuTreeList(queryListParentId(entity.getMenucode(), menuCodeList), menuCodeList);
			if (childList != null && !childList.isEmpty()) {
				entity.setChildList(childList);
			}
			subMenuList.add(entity);
		}

		return subMenuList;
	}
}
