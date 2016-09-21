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
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2016/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes ={ RedisConfig.class, Application.class})
public class RedisTest {
    @Autowired
    private StringRedisTemplate template;

    @Test
    public void setA(){
        final  byte[]  key ="bbb".getBytes();
         final  byte[]  value = "ccc".getBytes();
        final long liveTime = 10000L;
        template.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });


    }

}
