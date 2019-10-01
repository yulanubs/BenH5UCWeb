package cn.kotlincloud.user.front.controller.base;


import cn.kotlincloud.user.common.model.json.JsonBean;
import cn.kotlincloud.user.common.util.sys.CusAccessObjectUtil;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.front.app.UserFrontApplication;
import com.jfinal.core.NotAction;
import com.jfinal.log.Log;
import com.jfinal.server.undertow.UndertowConfig;
import io.jboot.app.JbootApplication;
import io.jboot.app.JbootApplicationConfig;
import io.jboot.web.controller.JbootController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.List;


/**
 * Created by yulan on 2019/8/20.
 */
public class BaseController extends JbootController {

    public static String resourcePath ="";
    @NotAction
    public JsonBean isProduceCode(Object ... object) {
        JsonBean jsonBean =new JsonBean();
        if (null!=object) {
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
     *
     * @return
     */
    public  String  gerResourcePath(){
        if (ValueUtils.isStrEmpty(resourcePath)){
           synchronized (BaseController.class){
               if (ValueUtils.isStrEmpty(resourcePath)){
                   JbootApplicationConfig appConfig = JbootApplication.getAppConfig(null);
                   UndertowConfig undertowConfig = JbootApplication.createUndertowConfig(appConfig);
                   resourcePath = undertowConfig.getResourcePath();
                   Log log = Log.getLog("==>:" );
                   log.debug("resourcePath:" + resourcePath);
               }
           }

        }
        return  resourcePath;
    }


}
