package com.junfly.water.service.sys;

import com.junfly.water.entity.sys.SysToken;

import java.util.Map;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface SysTokenService {

	SysToken queryByUserId(String userId);

	SysToken queryByToken(String token);
	
	void save(SysToken token);
	
	void update(SysToken token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(String userId);

}
