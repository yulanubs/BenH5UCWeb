package cn.kotlincloud.user.front.redis;

import cn.kotlincloud.user.common.util.sys.DESSecret;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import redis.clients.jedis.JedisCluster;

/**
 * Created by yulan on 2019/9/21.
 */
public class RedisUtil {

    /**
     * 获取Redis缓存实例
     * @return
     */

    public  static  JedisCluster getRedis(String name) {
        JedisCluster redis = RedisCluster.use(name);
        return redis;
    }


    public static   String reRedisCache( String key,String redisNmae){
        String ok="";
        //清空redis缓存,获取最新若干条数据
        String find_New_Service = DESSecret.String2MD5(key);
        //此案冲缓存获取数据
        JedisCluster redis = getRedis(redisNmae);
        if (ValueUtils.isNotEmpty(redis)){
            ok= redis.set(find_New_Service,"");
        }
        return  ok;
    }
}
