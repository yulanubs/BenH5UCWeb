package cn.kotlincloud.user.service.controller;

import cn.kotlincloud.user.common.util.sys.DESSecret;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.tool.redis.RedisUtil;
import io.jboot.Jboot;
import io.jboot.components.cache.redis.JbootRedisCacheConfig;
import io.jboot.exception.JbootIllegalConfigException;
import io.jboot.support.redis.JbootRedis;
import io.jboot.support.redis.JbootRedisManager;
import io.jboot.web.controller.annotation.RequestMapping;
import redis.clients.jedis.JedisCluster;

import static cn.kotlincloud.user.service.config.SerciceConstant.RedisBenH5UserServiceName;

/**
 * Created by yulan on 2019/9/21.
 */

@RequestMapping("/")
public class ServiceController extends BaseController {
    private JbootRedis redis;
    public  void  index(){


         redis = RedisUtil.getRedis();
        //此案冲缓存获取数据
        String string2MD5 = DESSecret.String2MD5("100000");
//        if (ValueUtils.isNotEmpty(redis)){
//            String set = redis.set(string2MD5, getSession().getId());
//            System.out.println("===>存入Redis:"+set);
//        }
//
//
//        //从redis中获取
//        if (ValueUtils.isNotEmpty(redis)){
//            String bannerdata = redis.get(string2MD5);
//            //获取成功，直接返回，失败，查询数据库
//            System.out.println("===>Redis中获取数据:"+bannerdata);
//
//        }


//        if (ValueUtils.isNotEmpty(redis)){
//            String set = redis.set(string2MD5,"123456");
//            System.out.println("===>存入Redis:"+set);
//        }

        //从redis中获取
        if (ValueUtils.isNotEmpty(redis)){
            String bannerdata = redis.get(string2MD5);
            //获取成功，直接返回，失败，查询数据库
            System.out.println("===>Redis中获取数据:"+bannerdata);

        }
        renderText(setTitle("用户中心Service"));
    }
}
