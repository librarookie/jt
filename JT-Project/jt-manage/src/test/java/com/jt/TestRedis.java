package com.jt;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chao
 * @date 2019/2/13 - 17:08
 */
public class TestRedis {

    @Test   // 操作String类型 (当做消息队列)
    public void testString() {
        Jedis jedis = new Jedis("192.168.220.142", 6379);
        jedis.set("name", "伍六七");
        String value = jedis.get("name");
        String a = jedis.get("a");
        System.out.println("a = " + a);
        System.out.println("value = " + value);
        jedis.close();
    }

    @Test   // 操作hash (当做消息对象)
    public void testHash() {
        Jedis jedis = new Jedis("192.168.220.142", 6379);
        jedis.hset("user","id","100");
        jedis.hset("user","name","伍六七");
        System.out.println("jedis = " + jedis.hget("user","id"));
        System.out.println("jedis = " + jedis.hget("user","name"));
        jedis.close();
    }

    @Test   // 操作list (redis 当做缓存使用)
    public void testList() {
        Jedis jedis = new Jedis("192.168.220.142", 6379);
        jedis.lpush("list", "1","2","3");
        for (int i = 0; i < 3; i++) {
            String valuel = jedis.lpop("list");
            System.out.println("lilo (栈) = " + valuel);
        }
        jedis.lpush("list", "4","5","6");
        for (int i = 0; i < 3; i++) {
            String valuer = jedis.rpop("list");
            System.out.println("liro (队列) = " + valuer);
        }
        jedis.close();
    }

    @Test   // 实现redis事务控制 (redis 当做数据库使用)
    public void testTx() {
        Jedis jedis = new Jedis("192.168.220.142", 6379);
        // 开启事务
        Transaction tx = jedis.multi();
        try {
            tx.set("dog","番茄");
            int a = 1/0;
            tx.exec();  // 执行 (提交事务)
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("执行失败, 事务回滚 ~");
            tx.discard();   //丢弃 (回滚事务)
        }
        jedis.close();
    }

    @Test   // redis分片操作
    public void testShards() {
        String host = "192.168.220.142";
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo(host, 6380));
        shards.add(new JedisShardInfo(host, 6381));
        shards.add(new JedisShardInfo(host, 6382));
        ShardedJedis shardedJedis = new ShardedJedis(shards);
        shardedJedis.set("shards", "完成分片操作 ~");
        System.out.println("获取数据 : "+shardedJedis.get("shards"));
    }
}
