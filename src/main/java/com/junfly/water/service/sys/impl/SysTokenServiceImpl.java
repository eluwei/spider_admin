package com.junfly.water.service.sys.impl;

import com.junfly.water.entity.sys.SysToken;
import com.junfly.water.mapper.sys.SysTokenMapper;
import com.junfly.water.service.sys.SysTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: pq
 * @Description:
 * @Date: 2017/5/24 17:28
 */
@Service("sysTokenService")
public class SysTokenServiceImpl implements SysTokenService {
    @Autowired
    private SysTokenMapper sysTokenDao;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public SysToken queryByUserId(String userId) {
        return sysTokenDao.queryByUserId(userId);
    }

    @Override
    public SysToken queryByToken(String token) {
        return sysTokenDao.queryByToken(token);
    }

    @Override
    public void save(SysToken token){
        sysTokenDao.save(token);
    }

    @Override
    public void update(SysToken token){
        sysTokenDao.update(token);
    }

    @Override
    public Map<String, Object> createToken(String userId) {
        //生成一个token
        String token = UUID.randomUUID().toString();
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysToken tokenEntity = queryByUserId(userId);
        if(tokenEntity == null){
            tokenEntity = new SysToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        }else{
            if(tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
                tokenEntity.setToken(token);
                tokenEntity.setUpdateTime(now);
                tokenEntity.setExpireTime(expireTime);

                //更新token
                update(tokenEntity);
            } else {
                token = tokenEntity.getToken();
                tokenEntity.setUpdateTime(now);
                tokenEntity.setExpireTime(expireTime);

                //更新token
                update(tokenEntity);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }
}
