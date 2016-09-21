package com.absurd.hello.dao;

import com.absurd.Application;
import com.absurd.core.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void setA() throws InterruptedException, ExecutionException{
        String key = "test-cas-1";
        ValueOperations<String, String> strOps = redisTemplate.opsForValue();
        strOps.set(key, "hello");
        ExecutorService pool  = Executors.newCachedThreadPool();
        List<Callable<Object>> tasks = new ArrayList<>();
        for(int i=0;i<5;i++){
            final int idx = i;
            tasks.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    return redisTemplate.execute(new SessionCallback() {
                        @Override
                        public Object execute(RedisOperations operations) throws DataAccessException {
                            operations.watch(key);
                            String origin = (String) operations.opsForValue().get(key);
                            operations.multi();
                            operations.opsForValue().set(key, origin + idx);
                            Object rs = operations.exec();
                            System.out.println("set:"+origin+idx+" rs:"+rs);
                            return rs;
                        }
                    });
                }
            });
        }
        List<Future<Object>> futures = pool.invokeAll(tasks);
        for(Future<Object> f:futures){
            System.out.println(f.get());
        }
        pool.shutdown();
        pool.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }

}
