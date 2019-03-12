package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Service
public class RedisService {

    /**
     * required=true(默认) 表示spring容器在启动时, 必须注入对象
     * required=false      表示当使用对象时才会注入
     * 有的工程需要，有的工程不需要。设置required=false，有就注入，没有就不注入。
     */
    @Autowired(required = false)
    private JedisSentinelPool jedisSentinelPool;

    public void set(String key, String value) {
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set(key, value);
        jedis.close();
    }

    public String get(String key) {
        Jedis jedis = jedisSentinelPool.getResource();
        String value = jedis.get(key);
        return value;
    }

}
