package com.junfly.water.mapper.sys;

import com.junfly.water.entity.sys.SysToken;
import com.junfly.water.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface SysTokenMapper extends BaseMapper<SysToken> {
    
    SysToken queryByUserId(String userId);

    SysToken queryByToken(String token);
	
}
