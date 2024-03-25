package com.example.accounta.config;
import lombok.Value;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration public class RedisRepositoryConfig {
    @Value("127.0.0.1") private String redisHost;
    @Value("6379") private int redisPort;
    @Bean public RedissonClient redissonClient(){
        Config config=new Config();config.useSingleServer().setAddress
        ("redis://"+redisHost+":"+redisPort);return Redisson.create(config);}
}