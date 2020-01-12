package io.nakong;

import com.google.gson.Gson;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import io.nakong.common.utils.RedisUtils;
import io.nakong.modules.sys.entity.SysUserEntity;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;
//    @Autowired
//    private HashOperations<String, String, Object> hashOperations;
//    @Autowired
//    private ListOperations<String, Object> listOperations;
//    @Autowired
//    private SetOperations<String, Object> setOperations;
//    @Autowired
//    private ZSetOperations<String, Object> zSetOperations;
    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;
    private final static Gson gson = new Gson();

    @Test
    public void contextLoads() {
        SysUserEntity user = new SysUserEntity();
        user.setEmail("qqq@qq.com");
         System.out.println(gson.toJson(user));


        set("user","test",-1);
        redisUtils.set("zhaoxinsheng", "haode ");
        Object object = redisUtils.get("zhaoxinsheng");
//        Object userEntity = get("user", -1 );

        System.out.println(ToStringBuilder.reflectionToString(object));get("user",-1);
    }


    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

    public void set(String key, String value, long expire){
        valueOperations.set(key, value);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }
    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }


}
