package com.jt.common.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chao
 * @date 2019/2/18 - 11:43
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster> {

    @Value("${redis.nodes}")    // 动态获取配置文件数据
    private String redisNodes;

    @Override
    public JedisCluster getObject() throws Exception {
        Set<HostAndPort> nodes = getNodes();
        return new JedisCluster(nodes);
    }

    private Set<HostAndPort> getNodes() {
        Set<HostAndPort> nodes = new HashSet<>();
        String[] strNodes = redisNodes.split(",");
        for (String node : strNodes) {
            String[] params = node.split(":");
            String host = params[0];
            int port = Integer.parseInt(params[1]);
            HostAndPort hostAndPort = new HostAndPort(host, port);
            nodes.add(hostAndPort);
        }
        return nodes;
    }

    @Override
    public Class<?> getObjectType() {
        return JedisCluster.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
