package com.example.accounta.config;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;
@Configuration public class LocalRedisConfig {
    @Value("${spring.redis.port}")
    private int redisPort;
    private RedisServer redisServer;
    @PostConstruct public void startRedis(){
        redisServer=new RedisServer(redisPort);
        redisServer.start();}
    @PreDestroy public void stopRedis(){
        if(redisServer!=null){redisServer.stop();}}}