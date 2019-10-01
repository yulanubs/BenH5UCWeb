package cn.kotlincloud.user.service.controller.user.extension;

import cn.kotlincloud.user.common.model.json.JsonBean;
import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.model.Userinfo;
import cn.kotlincloud.user.service.services.provider.UserinfoServiceProvider;
import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Inject;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

import static cn.kotlincloud.user.service.config.SerciceConstant.error_msg;

/**
 * Created by yulan on 2019/9/23.
 * 用户相关查询接口
 */
@RequestMapping("/user")
public class UserController  extends BaseController{

    @Inject
    public UserinfoServiceProvider usp;
    public  void  index(){
        renderText(setTitle("User接口！"));
    }

    /**
     * 根据用户ID查询
     */
    public  void  findById(){
        Integer userId = getInt("userId");
        if (ValueUtils.isEmpty(userId)||userId==0){
            renderText(error_msg);
            return;
        }
        Userinfo userinfo = usp.findById(userId);
        renderJson(isProduceCode(userinfo));
    }

    /**
     * 删除用户
     */
    public  void  delById(){
        Integer userId = getInt("userId");
        if (ValueUtils.isEmpty(userId)|userId==0){
            renderText(error_msg);
            return;
        }
        boolean del = usp.deleteById(userId);
        renderJson(isProduceCode(del));
    }
    /**
     * 修改用户信息
     */
    public  void  updateById(){
        Integer userId = getInt("userId");
        if (null==userId|userId==0){
            renderText(error_msg);
            return;
        }

        Userinfo userinfo = usp.findById(userId);

        //

        Object update = usp.saveOrUpdate(userinfo);
        renderJson(isProduceCode(update));
    }
    /**
     * 查询所有用户
     */
    public  void findAll(){
        List<Userinfo> userinfoList = usp.findAll();


        JsonBean jsonBean = isProduceCode( userinfoList );
        String toJSONString = JSON.toJSONString( jsonBean );
      renderText( toJSONString );
    }

}
