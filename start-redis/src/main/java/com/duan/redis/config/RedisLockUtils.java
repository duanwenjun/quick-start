package com.duan.redis.config;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;


/**
 * @author duanwj
 */
@Component
public class RedisLockUtils {

  private final StringRedisTemplate redisTemplate;

  @Autowired
  public RedisLockUtils (StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  /**
   * 尝试获取分布式锁
   *
   * @param lockKey    锁
   * @param requestId  请求标识
   * @param expireTime 过期时间单位：分钟
   * @return 是否获取成功
   */
  public synchronized boolean tryGetDistributedLock (String lockKey, String requestId, int expireTime) {
    boolean flag = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId);
    if (flag) {
      redisTemplate.expire(lockKey, expireTime, TimeUnit.MINUTES);
    }
    return flag;
  }

  /**
   * 释放分布式锁
   *
   * @param lockKey   锁
   * @param requestId 请求标识
   */
  public void releaseDistributedLock (String lockKey, Object requestId) {
    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    redisScript.setScriptText(script);
    redisScript.setResultType(Long.class);
    redisTemplate.execute(redisScript, Collections.singletonList(lockKey), requestId);
  }

}
