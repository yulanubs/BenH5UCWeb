package cn.kotlincloud.user.service.tool;

import cn.kotlincloud.user.common.util.sys.ValueUtils;

/**
 * Created by yulan on 2019/9/23.
 */
public class AppUtil {
    /**
     * 生成AppLey
     * @param userId 用户id
     * @param time  时间戳
     * @param appPackage app的包名
     * @return
     */
    public static String createrAppkey(int userId, long time, String appPackage) {
        String Appkey = MD5.GetMD5Code("" + userId + "" + time + appPackage);
        return Appkey;
    }

    /**
     * 生成rAppSecretKey
     * @param userId  用户id
     * @param time  时间戳
     * @param Appkey    应用的key
     * @return
     */
    public static String createrAppSecret(int userId, long time, String Appkey) {
        if (ValueUtils.isStrNotEmpty(Appkey)) {
            return MD5.GetMD5Code("" + userId + "" + time + Appkey);
        } else {
            return MD5.GetMD5Code("" + userId + "" + time);
        }

    }
}
