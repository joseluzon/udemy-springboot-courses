package com.github.joseluzon.udemy.springframework5.rest.config;

import java.util.Map;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.cache.RedisCacheConfiguration;
// import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
public class CacheConfig {

    // Default in memory
    // @Bean
    // public CacheManager getCacheManager() {
    // return new ConcurrentMapCacheManager("users");
    // }

    private String redisHost;
    private Integer redisPort;

    public CacheConfig(
        @Value("${spring.redis.host:localhost}") final String redisHost,
        @Value("${spring.redis.port:6379}") final Integer redisPort) {        
        this.redisHost = redisHost;
        this.redisPort = redisPort;
    }

    // Redisson redis client
    @Bean
    public RedissonClient redissonClient() {
        var config = new Config();
        config.useSingleServer().setAddress(String.format("redis://%s:%d", redisHost, redisPort.intValue()));
        return Redisson.create(config);
    }

    @Bean
    public CacheManager getCacheManager(final RedissonClient redissonClient) {
        var config = Map.of("users", new org.redisson.spring.cache.CacheConfig());
        return new RedissonSpringCacheManager(redissonClient, config);
    }

    // spring-boot-starter-data-redis
    // @Bean
    // public RedisCacheConfiguration cacheConfiguration() {
    //     return RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader()) // hack due to dev-tools : https://stackoverflow.com/questions/59638203/classcastexception-in-spring-redis-cache
    //         .entryTtl(Duration.ofMinutes(60))
    //         .disableCachingNullValues().serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    // }

    // @Bean
    // public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    //     return (builder) -> builder
    //         .withCacheConfiguration("users",
    //             RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader()) // hack due to dev-tools : https://stackoverflow.com/questions/59638203/classcastexception-in-spring-redis-cache
    //                 .entryTtl(Duration.ofMinutes(10)));
    // }
}
