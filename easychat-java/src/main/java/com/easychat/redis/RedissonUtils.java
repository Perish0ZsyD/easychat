package com.easychat.redis;

import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
/**
 * @ClassName RedissonUtils
 * @Description
 * @Author Siyuan
 * @Date 2024/11/16/15:05
 * @Version 1.0
 */
@Component("redissonUtils")
public class RedissonUtils<T> {

    @Resource
    private RedissonClient redissonClient;

    public void addSet(String key, T value, Long seconds) {
        RSet<T> reset = redissonClient.getSet(key);
        reset.add(value);
        reset.expire(seconds, TimeUnit.SECONDS);
    }
}