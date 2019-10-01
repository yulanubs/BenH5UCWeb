package cn.kotlincloud.user.service.controller.user.login;

import cn.kotlincloud.user.common.util.sys.DESSecret;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.model.Userinfo;
import cn.kotlincloud.user.service.entity.model.Users;
import cn.kotlincloud.user.service.entity.token.Tokens;
import cn.kotlincloud.user.service.services.provider.UserinfoServiceProvider;
import cn.kotlincloud.user.service.tool.TokenUtil;
import cn.kotlincloud.user.service.tool.redis.RedisUtil;
import com.jfinal.aop.Inject;
import io.jboot.support.redis.JbootRedis;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

import static cn.kotlincloud.user.service.config.SerciceConstant.error_msg;


/**
 * Created by yulan on 2019/9/22.
 */
@RequestMapping("/user/login")
public class LgonController extends BaseController {
    @Inject
    public UserinfoServiceProvider usp;
    private   String tokenKey="" ;
    public  void  index(){
        renderText(setTitle("UserLogin"));
    }


    /**
     * 账号密码是登录
     */
    public void loginByPwd(){
        String userName = get("userName");
        String userPwd = get("userPwd");
        Users users=new Users();
        if (ValueUtils.isStrEmpty(userName)||ValueUtils.isStrEmpty(userPwd))
        {
            renderText("必要参数不能为空！");
            return;
        }
        //登录操作
        String sql="select * from userinfo where userName = ? and pwdId = ?";
        List<Userinfo> userinfoList = usp.find(sql, userName, userPwd);
        if (ValueUtils.isListNotEmpty(userinfoList)){
            //登录成功,清除出该用户上次登录生成的token
            Userinfo userinfo = userinfoList.get(0);
            users.setUserInfo(userinfo);
            int userId=userinfo.getUserId();

            try {
                TokenUtil.removeTokens(userId);
                //清空redis

            } catch (Exception e) {
                // TODO Token移除失败
                e.printStackTrace();
            }
            //从缓存中获取Token
            users.setUserToken(getLoginToken( userPwd,userId ));




            renderJson(isProduceCode(users));
        }else {
            renderJson(isProduceCode(userinfoList));
        }

    }
    /**
     * 手机号验证码登录
     */
    public  void  loginByPhone(){
        String userMobile = get("userMobile");
        String smsCode = get("smsCode");
        String sql="SELECT * FROM  userinfo  WHERE userMobile = ?";
        //客户端验证后，服务端二次验证，



        // 验证成功后，根据手机号查询用户信息，并返回数据

        List<Userinfo> userinfos = usp.find(sql, userMobile);

        renderJson(isProduceCode(userinfos));
    }


    /**
     * 邮箱登录
     */

    public  void  loginByEmail(){
        String userEmail = get("userEmail");
        String userPwd = get("userPwd");
        String sql="select * from userinfo where userEmail = ? and pwdId = ?";

        List<Userinfo> userinfos = usp.find(sql, userEmail,userPwd);

        renderJson(isProduceCode(userinfos));
    }
    /**
     * QQ登录
     */
    public  void  loginByQQ(){
        String qqOpenId = get("qqOpenId");
        String sql="SELECT * FROM  userinfo  WHERE qqOpenId = ? ";
        List<Userinfo> userinfos = usp.find(sql, qqOpenId);

        renderJson(isProduceCode(userinfos));
    }
    /**
     * 微信登录
     */
    public  void  loginByWX(){
        String wxOpenId = get("wxOpenId");
      String sql="SELECT * FROM  userinfo  WHERE wxOpenId = ? ";
        List<Userinfo> userinfos = usp.find(sql, wxOpenId);

        renderJson(isProduceCode(userinfos));
    }
    /**
     * 人脸识别登录
     */
    public  void  loginByFace(){
        String userFaceId = get("faceId");//用户人脸特征注册id
        String faceKey = get("faceKey");//key人脸库识别成功返回
        String sql="SELECT * FROM  userinfo  WHERE face_id = ?";
        List<Userinfo> userinfos = usp.find(sql, userFaceId);

        renderJson(isProduceCode(userinfos));
    }
    /**
     * 声纹登录
     */
    public  void  loginByVoiceprint(){
        String voiceprintId = get("voiceprintId");//用户声纹特征注册id
        String faceKey = get("voiceprintKey");//key声纹库识别成功返回
        String sql="SELECT * FROM userinfo WHERE voiceprintId = ?";
        List<Userinfo> userinfos = usp.find(sql, voiceprintId);

        renderJson(isProduceCode(userinfos));
    }

    /**
     * sos紧急登录
     */
    public  void  loginBySOS(){
        String sosKey = get("sosKey");//终端初步识别后的sos指令key
        String sosStr = get("sosStr");//终端初步识别后的sos关键内容
        renderNull();
    }


/**
 * 统一登录接口
 */
public  void  loginAll(){
    String loginType = get( "loginType" );
    Integer appid = getInt( "appid" );
    if (null==appid|appid==0){
        renderText(error_msg);
        return;
    }
    if (appid==1005&&ValueUtils.isStrNotEmpty( loginType )){

            //
            if (loginType.equals( "xWeXin" )) {
                //转发请求，
                redirect( "/wx/login/onUserLoginByCode",true );

            }else  {

            }

    }else {

    }
}
}
