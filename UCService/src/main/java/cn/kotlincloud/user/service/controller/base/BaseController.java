package cn.kotlincloud.user.service.controller.base;


import cn.kotlincloud.user.common.model.json.JsonBean;
import cn.kotlincloud.user.common.util.sys.CusAccessObjectUtil;
import cn.kotlincloud.user.common.util.sys.DESSecret;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.entity.token.Tokens;
import cn.kotlincloud.user.service.tool.TokenUtil;
import cn.kotlincloud.user.service.tool.redis.RedisUtil;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import io.jboot.support.redis.JbootRedis;
import io.jboot.web.controller.JbootController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.List;

import static cn.kotlincloud.user.service.config.SerciceConstant.error_msg;


/**
 * Created by yulan on 2019/8/20.
 */
public class BaseController extends JbootController {

    public  void onJfinalConfigRoute(){

    }
    @NotAction
    public JsonBean isProduceCode(Object ... object) {
        JsonBean jsonBean =new JsonBean();
        if (ValueUtils.isNotEmpty( object )) {
            for (int i=0;i<object.length;i++){
                if (object.length > 0) {
                    if (object[i] instanceof Boolean &&Boolean.FALSE ==object[i]) {
                        jsonBean.setRespCode("-1");
                        jsonBean.setRespMsg("操作失败！");
                    } else if (object[i] instanceof List ){
                        List<Object> list = (List) object[i];
                       if (list.size()>0){
                            jsonBean.setData(object);
                        }else {
                           jsonBean.setRespCode("-1");
                           jsonBean.setRespMsg("操作失败！");
                       }
                    }else if (object[i] instanceof Array){
                     Array[] array= (Array[]) object[i];
                        if (array.length>0){
                            jsonBean.setData(object);
                        }else {
                            jsonBean.setRespCode("-1");
                            jsonBean.setRespMsg("操作失败！");
                        }

                    }else {
                        jsonBean.setData(object);
                    }
                }
            }
        }
        else {
            jsonBean.setRespCode("-1");
            jsonBean.setRespMsg("操作失败！");
        }


        return jsonBean;
    }



    /**
     * 请求对象
     * @param request
     * @return
     */
    @NotAction
    public  String getResUrl(HttpServletRequest request){

     return    request.getRequestURL().toString();
    }

    /**
     * 获取请求端的真实IP
     * @param request
     * @return
     */
    @NotAction
    public  String getResIP(HttpServletRequest request){
        return CusAccessObjectUtil.getIpAddress(request);

    }


    //将用户信息保存到Session
    @NotAction
    public  void setDataSession(String key ,Object v){
        HttpSession httpSession = getRequest().getSession();
        if (ValueUtils.isNotEmpty(httpSession)){
            httpSession.setAttribute(key,v);
        }

    }
    //从Session中获取数据
    @NotAction
    public  Object getDataSession(String key ){
        HttpSession httpSession = getRequest().getSession();
        if (ValueUtils.isNotEmpty(httpSession)){
          return   httpSession.getAttribute(key);
        }
        return  null;
    }

    /**
     * 判断是否登录
     * @return
     */
    @NotAction
    public  boolean  isLogin(){
//        User userInfo = (User) getDataSession(User_Login_info);
//        if (ValueUtils.isNotEmpty(userInfo)){
//            return  true;
//        }else {
//            return  false;
//        }
        return  false;
    }

    @NotAction
    public String  setTitle(String title){

        return "欢迎访问"+title;
    }

    /**
     * 设置处理失败返回信息
     * @param data
     * @param msg
     * @param code
     * @return
     */
    @NotAction
    public    JsonBean<Object>   setErrorMsg(Object data,String msg,String code){
        JsonBean<Object> jsonBean = new JsonBean<Object>();
        jsonBean.setData( data );
        jsonBean.setRespMsg( msg );
        jsonBean.setRespCode( code );
        return  jsonBean;
    }

    /**
     * 获取用户token
     * @param uniq
     * @param id
     * @return
     */
    public  String getLoginToken(String uniq,int id){
        try {
            TokenUtil.removeTokens(id);
            //清空redis

        } catch (Exception e) {
            // TODO Token移除失败
            RedisUtil.reRedisCache( String.valueOf(id) );
            e.printStackTrace();
        }
        String wxToken=null;
        JbootRedis redis = RedisUtil.getRedis();
        //从缓存中获取Token
        Tokens tokens = TokenUtil.generateTokens(uniq, id);
        if (ValueUtils.isNotEmpty(tokens)) {
            wxToken = tokens.getSignature();
            //此案冲缓存获取数据
            String string2MD5 = DESSecret.String2MD5(String.valueOf(id) );
            if (ValueUtils.isNotEmpty(redis)){
                String set = redis.set(string2MD5, wxToken);
                System.out.println("===>存入Redis:"+set);
            }


            //从redis中获取
            if (ValueUtils.isNotEmpty(redis)){
                String bannerdata = redis.get(string2MD5);
                //获取成功，直接返回，失败，查询数据库
                System.out.println("===>Redis中获取数据:"+bannerdata);

            }


        }

        return  wxToken;
    }
}
