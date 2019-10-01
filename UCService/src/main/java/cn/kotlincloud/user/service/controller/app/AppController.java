package cn.kotlincloud.user.service.controller.app;

import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.model.Appinfo;
import cn.kotlincloud.user.service.services.provider.AppinfoServiceProvider;
import cn.kotlincloud.user.service.tool.AppUtil;
import com.jfinal.aop.Inject;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

import static cn.kotlincloud.user.service.config.SerciceConstant.error_msg;

/**
 * Created by yulan on 2019/9/23.
 */
@RequestMapping("/app")
public class AppController extends BaseController {
    @Inject
 public     AppinfoServiceProvider asp;

    public  void  index(){
        renderJson(isProduceCode( setTitle( "App!" ) ));

    }

    /**
     * 新建app
     */
    public  void  creatApp(){
        Integer userId = getInt("userId");
        if (ValueUtils.isEmpty(userId)||userId==0){
            renderText(error_msg);
            return;
        }
        String appPackage = get( "appPackage" );
        String appType = get( "appType" );
        String appName = get( "appName" );
        String appUrl = get( "appUrl " );
        Appinfo appinfo =new Appinfo();
        appinfo.setAppType( appType );
        appinfo.setAppName( appName );
        appinfo.setAppUrl( appUrl );
        appinfo.setAppPackage( appPackage );
        appinfo.setCreateTime( new Date(  ) );
        appinfo.setCreatUser( userId );
        appinfo.setUpdateUser( userId );
        appinfo.setUpdateTime( new Date(  ) );
        //生成appId
        //生成appKey
        String appkey = AppUtil.createrAppkey( userId, new Date().getTime(), appPackage );
        //生成servicekey
        String appSecret = AppUtil.createrAppSecret( userId, new Date().getTime(), appkey );
        appinfo.setAppkey(appkey);
        appinfo.setServicekey( appSecret );
        Object save = asp.save( appinfo );

            if (ValueUtils.isNotEmpty( save )){
                appinfo.setId( (Integer) save );
                renderJson(isProduceCode( appinfo ));
            }else {
                renderJson(isProduceCode( save ));
            }




    }

    /**
     * 录入一个拥有app信息
     */
    public void addApp(){
        Integer userId = getInt("userId");
        if (ValueUtils.isEmpty(userId)||userId==0){
           renderText( error_msg );
            return;
        }
////登录渠道:1001 ios手机 1002 android手机 1003 微信小程序 1004 手机H5
        String appkey = get( "appkey" );
        String appSecret = get( "appSecret" );
        String appType = get( "appType" );
        String appName = get( "appName" );
        String appUrl = get( "appUrl " );
        Appinfo appinfo =new Appinfo();
        appinfo.setAppType( appType );
        appinfo.setAppName( appName );
        appinfo.setAppUrl( appUrl );
        appinfo.setCreateTime( new Date(  ) );
        appinfo.setCreatUser( userId );
        appinfo.setUpdateUser( userId );
        appinfo.setUpdateTime( new Date(  ) );
        appinfo.setAppkey( appkey );
        appinfo.setServicekey( appSecret );
        Object save = asp.save( appinfo );
    if (ValueUtils.isNotEmpty( save )){
        appinfo.setId( (Integer) save );
        renderJson(isProduceCode( appinfo ));
    }else {
        renderJson(isProduceCode( save ));
    }
    }
    /**
     * 查询首页应用
     */

    public  void  findAll(){
        List<Appinfo> appinfoList = asp.findAll();
        renderJson(isProduceCode( appinfoList ));
    }
    /**
     * 根据id查询
     */

    public  void  findById(){
        Integer appId = getInt("appId");
        if (ValueUtils.isEmpty(appId)||appId==0){
            renderText(error_msg);
            return;
        }

        Appinfo appinfo = asp.findById( appId );

        renderJson(isProduceCode( appinfo ));
    }

    /**
     * 根据id删除
     */

    public  void  delById(){
        Integer appId = getInt("appId");
        if (ValueUtils.isEmpty(appId)||appId==0){
            renderText(error_msg);
            return;
        }
        boolean del = asp.deleteById( appId );
        renderJson(isProduceCode( del ));
    }


    /**
     * 根据id修改
     */

    public  void  updateById(){
        Integer appId = getInt("appId");
        if (ValueUtils.isEmpty(appId)||appId==0){
            renderText(error_msg);
            return;
        }
        Integer userId = getInt("userId");
        String appPackage = get( "appPackage" );
        String appType = get( "appType" );
        String appName = get( "appName" );
        String appUrl = get( "appUrl " );
        Appinfo appinfo =new Appinfo();
        appinfo.setId( appId );
        appinfo.setAppType( appType );
        appinfo.setAppName( appName );
        appinfo.setAppUrl( appUrl );
        appinfo.setAppPackage( appPackage );
        appinfo.setUpdateUser( userId );
        appinfo.setUpdateTime( new Date(  ) );

        Object update = asp.saveOrUpdate( appinfo );
        renderJson(isProduceCode( appinfo ));
    }

    /**
     * 更新appSecret
     */

    public  void  updateAppSecret(){
        Integer appId = getInt("appId");
        if (ValueUtils.isEmpty(appId)||appId==0){
            renderText(error_msg);
            return;
        }
        Integer userId = getInt("userId");
        String appKey = get( "appKey" );
        //生成servicekey
        String appSecret = AppUtil.createrAppSecret( userId, new Date().getTime(), appKey );

        Appinfo appinfo = new Appinfo();
        appinfo.setServicekey( appSecret );
        appinfo.setUpdateUser( userId );
        appinfo.setUpdateTime( new Date(  ) );
        Object update = asp.saveOrUpdate( appinfo );
        renderJson(isProduceCode( update ));

    }
}
