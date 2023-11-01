//package com.example.demo;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import redis.clients.jedis.Jedis;
//
//import java.util.Map;
//import java.util.Set;
//
//public class JedisPoolTest {
//
//    private Jedis jedis;
//
//    @BeforeEach
//    void setUp(){
//        // 1.建立连接
//        jedis = JedisConnectionFactory.getJedis();
//        // 2.选择库
//        jedis.select(0);
//    }
//
//    @Test
//    void testString(){
//        Set<String> keys = jedis.keys("*");
//        System.out.println(keys);
//
//        Map<String, String> stringMap = jedis.hgetAll("mmy:user:1");
//        System.out.println(stringMap);
//    }
//
//    @AfterEach
//    void tearDown(){
//        if (jedis != null){
//            jedis.close();
//        }
//    }
//}
