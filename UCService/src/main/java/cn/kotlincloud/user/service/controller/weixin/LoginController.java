package cn.kotlincloud.user.service.controller.weixin;

import cn.kotlincloud.user.common.model.json.JsonBean;
import cn.kotlincloud.user.common.util.sys.DESSecret;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.common.util.wx.WXCore;
import cn.kotlincloud.user.service.config.WeiXinConfig;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.model.UserPwd;
import cn.kotlincloud.user.service.entity.model.Userinfo;
import cn.kotlincloud.user.service.entity.model.Users;
import cn.kotlincloud.user.service.entity.token.Tokens;
import cn.kotlincloud.user.service.entity.wx.WXLoginInfo;
import cn.kotlincloud.user.service.entity.wx.WxUserInfo;
import cn.kotlincloud.user.service.services.provider.UserPwdServiceProvider;
import cn.kotlincloud.user.service.services.provider.UserinfoServiceProvider;
import cn.kotlincloud.user.service.tool.TokenUtil;
import cn.kotlincloud.user.service.tool.redis.RedisUtil;
import com.jfinal.aop.Inject;
import com.jfinal.json.Json;
import io.jboot.support.redis.JbootRedis;
import io.jboot.utils.HttpUtil;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static cn.kotlincloud.user.service.config.SerciceConstant.error_msg;

/**
 * Created by yulan on 2019/9/24.
 */
@RequestMapping("/wx/login")
public class LoginController extends BaseController {
    @Inject
    public UserinfoServiceProvider usp;
    @Inject
    public UserPwdServiceProvider upsp;
    private   String wxToken="" ;
    public  void  index(){
        renderJson(isProduceCode( setTitle( "小程序登录" ) ));
    }

    /**
     * 微信用户注册或者登录接口
     */
    public void onUserLoginByCode(){

        String code = get( "code" );

        if (ValueUtils.isStrEmpty( code )){
            JsonBean<Object> jsonBean = new JsonBean<>();
            jsonBean.setData( "" );
            jsonBean.setRespMsg( error_msg );
            jsonBean.setRespCode( "-1" );
            renderJson(jsonBean);
            return;
        }
        Users users=new Users();
        String loginType = get( "loginType" );
        String rawData = get("rawData");
        String userCode = String.valueOf(new Date().getTime());
        String pwdCode = String.valueOf(new Date().getTime());
        String wxAppId = get( "wxAppId" );
        Integer appid = getInt( "appid" );
        String version = get( "version" );
        String sign = get( "sign" );
        String timestamp = get( "timestamp" );
        String loginChannel = get( "loginChannel" );


        WxUserInfo wxUserInfo=null;
        if (ValueUtils.isStrNotEmpty( rawData )){
            Json json = Json.getJson();
             wxUserInfo = json.parse( rawData, WxUserInfo.class );


        }
        HashMap<String,Object> paras =new HashMap<>(  );
        paras.put( "appid",WeiXinConfig.AppID );
        paras.put( "secret",WeiXinConfig.AppSecret );
        paras.put( "js_code",code);
        paras.put( "grant_type",WeiXinConfig.grant_type);

        String res = HttpUtil.httpGet( WeiXinConfig.ApiJscode2session, paras );
        Json json = Json.getJson();
        WXLoginInfo wxLoginInfo = json.parse( res, WXLoginInfo.class );
      String   openid = wxLoginInfo.getOpenid();
        if (ValueUtils.isNotEmpty( wxLoginInfo )&&ValueUtils.isStrNotEmpty( openid)){
            users.setWxLoginInfo( wxLoginInfo );
            //经理登录
            String sql="SELECT * FROM  userinfo  WHERE wxOpenId = ? ";
            List<Userinfo> userinfoList = usp.find(sql, openid);
            if (ValueUtils.isListNotEmpty( userinfoList )){
                Userinfo userInfo = userinfoList.get( 0 );
                userInfo.setPwdId( null );
                users.setUserInfo(userInfo );

            }else {
                //邮箱注册
                UserPwd pwd = new UserPwd();
                pwd.setPwdCode(pwdCode);
                pwd.setUserCode(userCode);
                pwd.setUpdateTime(new Date());
                upsp.save( pwd );

                //用户注册
                Userinfo userInfo = new Userinfo();
                if (ValueUtils.isNotEmpty( wxUserInfo )){
                    userInfo.setUserByName(wxUserInfo.getNickName());
                    userInfo.setUserSex( wxUserInfo.getGender() );
                    userInfo.setUserIconUrl(wxUserInfo.getAvatarUrl());
                    userInfo.setLanguage( wxUserInfo.getLanguage() );
                    userInfo.setCountry( wxUserInfo.getCountry() );
                    userInfo.setProvince( wxUserInfo.getProvince() );
                    userInfo.setCity( wxUserInfo.getCity() );
                }
                userInfo.setWxOpenId(openid);

                userInfo.setPwdId(pwdCode);
                userInfo.setAppId( appid );
                userInfo.setUserType( 2 );
                userInfo.setPubtime(new Date());
                userInfo.setUserCode(userCode);
                int userId = (int) usp.save(userInfo);
                //注册成后自动返回登录信息
                userInfo.setUserId(userId);
                userInfo.setPwdId(null);
                userInfo.setPubtime( null );
                users.setUserInfo(userInfo);


            }
            //获取token
          int  userId=users.getUserInfo().getUserId();
            //从缓存中获取Token
            users.setUserToken(getLoginToken( wxAppId,userId ));


        }



        System.out.println(res);
           renderJson( isProduceCode( users ) );


    }

/**
 * 根据code获取登录凭证
 */
    public  void  getLoginData(){

        String code = get( "code" );

        if (ValueUtils.isStrEmpty( code )){
            renderJson(setErrorMsg( "",error_msg,"-1" ));
            return;
        }
        Users users=new Users();
        String wxAppId = get( "wxAppId" );
        Integer appid = getInt( "appid" );
        String version = get( "version" );
        String sign = get( "sign" );
        String timestamp = get( "timestamp" );
        String loginChannel = get( "loginChannel" );
        HashMap<String,Object> paras =new HashMap<>(  );
        paras.put( "appid",WeiXinConfig.AppID );
        paras.put( "secret",WeiXinConfig.AppSecret );
        paras.put( "js_code",code);
        paras.put( "grant_type",WeiXinConfig.grant_type);

        String res = HttpUtil.httpGet( WeiXinConfig.ApiJscode2session, paras );
        Json json = Json.getJson();
        WXLoginInfo wxLoginInfo = json.parse( res, WXLoginInfo.class );
        System.out.println(res);
        users.setWxLoginInfo( wxLoginInfo );
        renderJson(isProduceCode( users ));
    }
}
