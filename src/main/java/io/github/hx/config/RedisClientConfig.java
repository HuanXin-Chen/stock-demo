package io.github.hx.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author：3500
 * @date: 2024-03-06
 * @Copyright： 公众号：3500的杂记
 */
@Configuration
@EnableConfigurationProperties(RedisClientConfigProperties.class)
public class RedisClientConfig {

    /**
     * 参考官方文档quick start可知配置方式
     */
    @Bean("redissonClient")
    public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext, RedisClientConfigProperties properties) {
        Config config = new Config();
        JsonJacksonCodec codec = new JsonJacksonCodec();

        config.setCodec(codec);

        config.useSingleServer()
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
                .setConnectionPoolSize(properties.getPoolSize())
                .setConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setIdleConnectionTimeout(properties.getIdleTimeout())
                .setConnectTimeout(properties.getConnectTimeout())
                .setRetryAttempts(properties.getRetryAttempts())
                .setRetryInterval(properties.getRetryInterval())
                .setPingConnectionInterval(properties.getPingInterval())
                .setKeepAlive(properties.isKeepAlive()); // lombok对于Boolean生成的是is类型判断

        //System.out.println("redis连接池配置成功");


        return Redisson.create(config);
    }
}