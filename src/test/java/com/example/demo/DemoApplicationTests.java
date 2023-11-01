//package com.example.demo;
//
//import com.example.demo.entity.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import java.util.Map;
//
//
//@SpringBootTest
//class DemoApplicationTests {
//
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//
//	@Autowired
//	private RedisTemplate redisTemplate;
//
//	private static final ObjectMapper mapper = new ObjectMapper();
//
//	@Test
//	void testString() throws JsonProcessingException {
//
////		User caoCao = new User("曹操", 88, 175);
////		String jsonStr = mapper.writeValueAsString(caoCao);
////		stringRedisTemplate.opsForValue().set("heima:user:4",jsonStr);
////		String userStr = stringRedisTemplate.opsForValue().get("heima:user:4");
////		System.out.println(userStr);
////		User user = mapper.readValue(userStr, User.class);
////		System.out.println(user);
//
//		// 对Hash的操作
//		stringRedisTemplate.opsForHash().put("heima:user:5","name","胡歌");
//		stringRedisTemplate.opsForHash().put("heima:user:5","age","22");
//
//		Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("heima:user:5");
//		System.out.println(entries);
//
//
////		User caoCao = new User("曹操", 88, 175);
////		redisTemplate.opsForValue().set("heima:user:4",caoCao);
////		User userStr = (User) redisTemplate.opsForValue().get("heima:user:4");
////		System.out.println(userStr);
//
//
//	}
//
//}
