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
//public class JedisTest {
//
//    private Jedis jedis;
//
//    @BeforeEach
//    void setUp(){
//        // 1.��������
//        jedis = new Jedis("192.168.20.128", 6379);
//        // 2.����
//        jedis.auth("733806_Mmy");
//        // 3.ѡ���
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
