package cn.kotlincloud.user.front.controller.front;


import cn.kotlincloud.user.front.app.UserFrontApplication;
import cn.kotlincloud.user.front.config.FrontConfig;
import cn.kotlincloud.user.front.controller.base.BaseController;
import io.jboot.app.JbootApplication;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by yulan on 2019/9/20.
 */
@RequestMapping("/")
public class FrontController extends BaseController {

    public  void index(){

//        setAttr("netWebTitle","BenH5用户中心");
//        resourcePath
        render(gerResourcePath()+"/index.html");
    }
//    public  void  getIndexJs(){
//        setAttr("netWebTitle","BenH5用户中心");
//        render("index.js");
//    }
//    public  void  getCommonJs(){
//        setAttr("netWebTitle","BenH5用户中心");
//        render("common.js");
//    }
}
