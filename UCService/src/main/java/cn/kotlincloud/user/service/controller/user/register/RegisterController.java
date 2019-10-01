package cn.kotlincloud.user.service.controller.user.register;

import cn.kotlincloud.user.common.util.sys.DESSecret;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.model.UserPwd;
import cn.kotlincloud.user.service.entity.model.Userinfo;
import cn.kotlincloud.user.service.entity.model.Users;
import cn.kotlincloud.user.service.entity.token.Tokens;
import cn.kotlincloud.user.service.services.provider.UserPwdServiceProvider;
import cn.kotlincloud.user.service.services.provider.UserinfoServiceProvider;
import cn.kotlincloud.user.service.tool.TokenUtil;
import cn.kotlincloud.user.service.tool.redis.RedisUtil;
import com.jfinal.aop.Inject;
import io.jboot.support.redis.JbootRedis;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by yulan on 2019/9/23.
 */
@RequestMapping("/user/register")
public class RegisterController extends BaseController {
    @Inject
    public UserPwdServiceProvider upsp;
    @Inject
    public UserinfoServiceProvider usp;
    public  void  index(){

        renderText(setTitle("User注册!"));
    }

    /**
     * 用户密密注册
     */
    public  void regByPwd() {
        String userName = get("userName");
        String userPwd = get("userPwd");
        Integer appId = getInt( "appId" );
        String userByName = get("userByName");
        String userIconUrl = get("userIconUrl");
        String userCode = String.valueOf(new Date().getTime());
        String pwdCode = String.valueOf(new Date().getTime());


        //密码注册
        UserPwd pwd = new UserPwd();
        pwd.setPwdCode(pwdCode);
        pwd.setUserCode(userCode);
        pwd.setPwd(userPwd);
        pwd.setUpdateTime(new Date());
        upsp.save( pwd );
        //用户注册
        Users users = new Users();
        Userinfo userinfo = new Userinfo();

        userinfo.setUserName(userName);
        if (ValueUtils.isStrEmpty(userByName)) {
            userinfo.setUserByName(userName);
        } else {
            userinfo.setUserByName(userByName);
        }
        userinfo.setPwdId(pwdCode);
        userinfo.setPubtime(new Date());
        userinfo.setUserCode(userCode);
        userinfo.setAppId( appId );
        userinfo.setUserType( 2 );
        userinfo.setUserIconUrl(userIconUrl);
        int userId = (int) usp.save(userinfo);
        //注册成后自动返回登录信息
        userinfo.setUserId(userId);
        userinfo.setPwdId(null);
        userinfo.setAppId( null );
        userinfo.setUserName( null );
        userinfo.setPubtime( null );
        users.setUserInfo(userinfo);
        try {
            TokenUtil.removeTokens(userId);
            //清空redis

        } catch (Exception e) {
            // TODO Token移除失败
            e.printStackTrace();
        }
        JbootRedis redis = RedisUtil.getRedis();
        //从缓存中获取Token
        Tokens tokens = TokenUtil.generateTokens(userPwd, userId);
        if (ValueUtils.isNotEmpty(tokens)) {
            String tokenKey = tokens.getSignature();
            users.setUserToken( tokenKey);

            //此案冲缓存获取数据
            String string2MD5 = DESSecret.String2MD5(String.valueOf(userId));
            if (!(null == redis)) {
                String set = redis.set(string2MD5, tokenKey);
                System.out.println("===>存入Redis:" + set);
            }

            renderJson(isProduceCode(users));


        }
    }

    /**
     * 邮箱注册
     */
    public void  regByEmail(){
        String userEmail = get("userEmail");
        String userPwd = get("userPwd");
        Integer appId = getInt( "appId" );
        String userByName = get("userByName");
        String userIconUrl = get("userIconUrl");
        String userCode = String.valueOf(new Date().getTime());
        String pwdCode = String.valueOf(new Date().getTime());


        //邮箱注册
        UserPwd pwd = new UserPwd();
        pwd.setPwdCode(pwdCode);
        pwd.setUserCode(userCode);
        pwd.setPwd(userPwd);
        pwd.setUpdateTime(new Date());
        upsp.save( pwd );
        //用户注册
        Users users = new Users();
        Userinfo userinfo = new Userinfo();

        userinfo.setUserEmail(userEmail);
        userinfo.setUserByName(userByName);
        userinfo.setPwdId(pwdCode);
        userinfo.setAppId( appId );
        userinfo.setUserType( 2 );
        userinfo.setPubtime(new Date());
        userinfo.setUserIconUrl(userIconUrl);
        userinfo.setUserCode(userCode);
        int userId = (int) usp.save(userinfo);
        //注册成后自动返回登录信息
        userinfo.setUserId(userId);
        userinfo.setPwdId(null);
        userinfo.setAppId( null );
        userinfo.setUserEmail( null );
        userinfo.setPubtime( null );
        users.setUserInfo(userinfo);
        try {
            TokenUtil.removeTokens(userId);
            //清空redis

        } catch (Exception e) {
            // TODO Token移除失败
            e.printStackTrace();
        }
        JbootRedis redis = RedisUtil.getRedis();
        //从缓存中获取Token
        Tokens tokens = TokenUtil.generateTokens(userPwd, userId);
        if (ValueUtils.isNotEmpty(tokens)) {
            String tokenKey = tokens.getSignature();
            users.setUserToken(tokenKey);

            //此案冲缓存获取数据
            String string2MD5 = DESSecret.String2MD5(String.valueOf(userId));
            if (!ValueUtils.isEmpty(redis)) {
                String set = redis.set(string2MD5, tokenKey);
                System.out.println("===>存入Redis:" + set);
            }

            renderJson(isProduceCode(users));


        }
    }

    /**
     * 微信注册
     */

    public  void  regByWX(){
        String wxOpenId = get("wxOpenId");
        Integer appId = getInt( "appId" );
        String userByName = get("userByName");
        String userIconUrl = get("userIconUrl");
        String userCode = String.valueOf(new Date().getTime());
        String pwdCode = String.valueOf(new Date().getTime());


        //邮箱注册
        UserPwd pwd = new UserPwd();
        pwd.setPwdCode(pwdCode);
        pwd.setUserCode(userCode);
        pwd.setUpdateTime(new Date());
        upsp.save( pwd );
        //用户注册
        Users users = new Users();
        Userinfo userinfo = new Userinfo();

        userinfo.setWxOpenId(wxOpenId);
        userinfo.setUserByName(userByName);
        userinfo.setPwdId(pwdCode);
        userinfo.setAppId( appId );
        userinfo.setUserType( 2 );
        userinfo.setPubtime(new Date());
        userinfo.setUserIconUrl(userIconUrl);
        userinfo.setUserCode(userCode);
        int userId = (int) usp.save(userinfo);
        //注册成后自动返回登录信息
        userinfo.setUserId(userId);
        userinfo.setPwdId(null);
        userinfo.setWxOpenId( null );
        userinfo.setAppId( null );
        userinfo.setPubtime( null );
        users.setUserInfo(userinfo);
        try {
            TokenUtil.removeTokens(userId);
            //清空redis

        } catch (Exception e) {
            // TODO Token移除失败
            e.printStackTrace();
        }
        JbootRedis redis = RedisUtil.getRedis();
        //从缓存中获取Token
        Tokens tokens = TokenUtil.generateTokens(wxOpenId, userId);
        if (ValueUtils.isNotEmpty(tokens)) {
            String tokenKey = tokens.getSignature();
            users.setUserToken(tokenKey);

            //此案冲缓存获取数据
            String string2MD5 = DESSecret.String2MD5(String.valueOf(userId));
            if (null!=redis) {
                String set = redis.set(string2MD5, tokenKey);
                System.out.println("===>存入Redis:" + set);
            }

            renderJson(isProduceCode(users));


        }
    }
}
