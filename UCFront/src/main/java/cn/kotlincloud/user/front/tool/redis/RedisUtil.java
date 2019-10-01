package cn.kotlincloud.user.front.tool.redis;

import cn.kotlincloud.user.common.util.sys.DESSecret;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import io.jboot.Jboot;
import io.jboot.components.cache.redis.JbootRedisCacheConfig;
import io.jboot.exception.JbootIllegalConfigException;
import io.jboot.support.redis.JbootRedis;
import io.jboot.support.redis.JbootRedisManager;

/**
 * Created by yulan on 2019/9/21.
 */
public class RedisUtil {

    /**
     * 获取Redis缓存实例
     * @return
     */

    public  static  JbootRedis getRedis() {
        JbootRedis redis =null;

        JbootRedisCacheConfig redisConfig = Jboot.config(JbootRedisCacheConfig.class);

        //优先使用 jboot.cache.redis 的配置
        if (redisConfig.isConfigOk()) {
            redis = JbootRedisManager.me().getRedis(redisConfig);
        }
        // 当 jboot.cache.redis 配置不存在时，
        // 使用 jboot.redis 的配置
        else {
            redis = Jboot.getRedis();
        }

        if (redis == null) {
            throw new JbootIllegalConfigException("can not get redis, please check your jboot.properties , please correct config jboot.cache.redis.host or jboot.redis.host ");
        }
        return redis;
    }


    public static   String reRedisCache( String key){
        String ok="";
        //清空redis缓存,获取最新若干条数据
        String find_New_Service = DESSecret.String2MD5(key);
        //此案冲缓存获取数据
        JbootRedis redis =getRedis();


        if (redis == null) {
            throw new JbootIllegalConfigException("can not get redis, please check your jboot.properties , please correct config jboot.cache.redis.host or jboot.redis.host ");
        }
        if (ValueUtils.isNotEmpty(redis)){
            ok= redis.set(find_New_Service,"");
        }
        return  ok;
    }
}
