package com.example.redisdemo.config;

import com.example.redisdemo.controller.GreetingController;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisCommands connectionFactory() {
        log.info("-------------------- Connection Factory --------------------");
        //RedisURI redisUri = RedisURI.Builder
        //        .sentinel("redis-sentinel", "redis-sentinel")
        //        .withSentinel("redis2-sentinel").build();
        RedisURI redisUri = RedisURI.Builder
                .sentinel("cluster1-redis-sentinel", "redis-sentinel")
                .withSentinel("cluster2-redis-sentinel")
                .withSentinel("cluster3-redis-sentinel")
                .build();


        RedisClient redisClient = RedisClient.create(redisUri);
        StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
        return redisConnection.sync();
    }
}