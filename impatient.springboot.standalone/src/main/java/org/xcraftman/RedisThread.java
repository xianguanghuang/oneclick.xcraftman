package org.xcraftman;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by xianguanghuang on 2016/3/14. For Note and Demo purpose
 */
public class RedisThread implements Runnable{

    private StringRedisTemplate stringRedisTemplate;

    public RedisThread(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public void run() {
        for (int i = 0 ; i < 10000; i ++){
            stringRedisTemplate.opsForValue().append("b","bb");


        }
        System.out.println("thread run done");
    }
}
