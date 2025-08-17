package com.scaler.novprojectmodule.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

   public RedisTemplate<String,Object> redisTemplate (){
       // Create an object of RedisTemplate and define datatype
       RedisTemplate<String,Object> template = new RedisTemplate<>();
       // Setting a connection factory
       template.setConnectionFactory(jedisConnectionFactory());
       return template;
   }

}
