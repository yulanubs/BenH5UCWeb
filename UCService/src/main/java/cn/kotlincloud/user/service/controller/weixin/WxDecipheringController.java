package cn.kotlincloud.user.service.controller.weixin;

import cn.kotlincloud.user.common.model.json.JsonBean;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.common.util.wx.WXCore;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.wx.PhoneNumber;
import cn.kotlincloud.user.service.entity.wx.WXLoginInfo;
import cn.kotlincloud.user.service.tool.WxUtil;
import com.jfinal.json.Json;
import io.jboot.web.controller.annotation.RequestMapping;

import static cn.kotlincloud.user.service.config.SerciceConstant.error_msg;

/**
 * Created by yulan on 2019/9/25.
 */
@RequestMapping("/v1/wx/deciphering")
public class WxDecipheringController extends BaseController {
    /**
     * 解密并且获取用户手机号码
     * @return
     * @throws Exception
     */
    public  void  index(){
        renderJson(isProduceCode( setTitle( "微信解密接口！" ) ));
    }

    /**
     * 解密手机号码
     */
    public void  getPhoneNumber(){

        String encrypdata = get( "encrypdata" );
        String ivdata = get( "ivdata" );
        String sessionkey = get( "sessionKey" );
        String wxAppId = get( "wxAppId" );
        String appId  = get( "appId " );
        if(ValueUtils.isStrEmpty( encrypdata )||ValueUtils.isStrEmpty( ivdata )||ValueUtils.isStrEmpty( sessionkey )){
            renderJson(setErrorMsg( "", error_msg,"-1"));
            return;
        }
        String data  = WXCore.decrypt(  encrypdata, sessionkey, ivdata );
//        String data = WxUtil.deciphering( encrypdata, ivdata, sessionkey );
       if (ValueUtils.isStrEmpty( data )){
           renderJson(setErrorMsg( "", error_msg,"-1"));
       }else {
           System.out.println( data );
           Json json = Json.getJson();
           PhoneNumber phoneNumber = json.parse( data, PhoneNumber.class );

           renderJson( isProduceCode( phoneNumber )  );
       }


    }


}
