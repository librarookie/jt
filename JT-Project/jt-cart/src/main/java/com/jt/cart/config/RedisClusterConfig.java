package com.jt.cart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chao
 * @date 2019/2/20 - 11:36
 */
@Configuration   // 标记这是一个配置类
@PropertySource("classpath:/properties/redis.properties")   // 引入配置文件
public class RedisClusterConfig {

    @Value("${redis.nodes}")
    private String  redisNodes;

    @Bean
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        String[] hostAndPorts = redisNodes.split(",");
        for (String node : hostAndPorts) {
            String[] args = node.split(":");
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            nodes.add(new HostAndPort(host, port));
        }
        return new JedisCluster(nodes);
    }
}
