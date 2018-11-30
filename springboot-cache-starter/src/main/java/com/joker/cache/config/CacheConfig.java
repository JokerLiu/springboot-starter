package com.joker.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/11/30 21:48
 */
@Slf4j
@Configuration
@EnableCaching//启用缓存的意思
public class CacheConfig extends CachingConfigurerSupport {


    @Configuration
    static class MyRedisTemplateConfig {

        private Duration timeToLive = Duration.ZERO;

        public void setTimeToLive(Duration timeToLive) {
            this.timeToLive = timeToLive;
        }

        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(factory);
            template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
            return template;
        }

        @Bean
        public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(this.timeToLive)
                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                    .disableCachingNullValues();

            RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                    .cacheDefaults(config)
                    .transactionAware()
                    .build();

            log.debug("自定义RedisCacheManager加载完成");
            return redisCacheManager;
        }

        private RedisSerializer<String> keySerializer() {
            return new StringRedisSerializer();
        }

        private RedisSerializer<Object> valueSerializer() {
            return new GenericJackson2JsonRedisSerializer();
        }

    }

}