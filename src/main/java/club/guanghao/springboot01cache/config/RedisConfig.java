package club.guanghao.springboot01cache.config;

import club.guanghao.springboot01cache.entity.Department;
import club.guanghao.springboot01cache.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.net.UnknownHostException;

/**
 * @author 黄光昊
 * @create 2020-05-15-19:15
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     * 公用缓存管理器
     * @param connectionFactory
     * @return
     */
    @Primary//主缓存管理器
    @Bean
    public RedisCacheManager RedisCacheManger(RedisConnectionFactory connectionFactory){

        //初始化一个RedisCacheWriter输出流
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //采用Jackson2JsonRedisSerializer序列化机制
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        //创建一个RedisSerializationContext.SerializationPair给定的适配器pair
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        //创建CacheConfig
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        return new RedisCacheManager(redisCacheWriter, configuration);
    }

    /**
     * 员工缓存管理器
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager empRedisCacheManger(RedisConnectionFactory connectionFactory){

        //初始化一个RedisCacheWriter输出流
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //采用Jackson2JsonRedisSerializer序列化机制
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        //创建一个RedisSerializationContext.SerializationPair给定的适配器pair
        RedisSerializationContext.SerializationPair<Employee> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        //创建CacheConfig
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        return new RedisCacheManager(redisCacheWriter, configuration);
    }

    /**
     * 部门缓存管理器
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager deptRedisCacheManger(RedisConnectionFactory connectionFactory){

        //初始化一个RedisCacheWriter输出流
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //采用Jackson2JsonRedisSerializer序列化机制
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        //创建一个RedisSerializationContext.SerializationPair给定的适配器pair
        RedisSerializationContext.SerializationPair<Department> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        //创建CacheConfig
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        return new RedisCacheManager(redisCacheWriter, configuration);
    }
}
