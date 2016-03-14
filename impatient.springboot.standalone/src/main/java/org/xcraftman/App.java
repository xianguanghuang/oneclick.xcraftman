package org.xcraftman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableAutoConfiguration
public class App implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
       for (int i = 0; i < 10; i++){
           RedisThread redisThread = new RedisThread(stringRedisTemplate);
           new Thread(redisThread).start();
       }
        System.out.println("Thread count done");


        Thread.currentThread().join();
    }
}
