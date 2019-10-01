package cn.kotlincloud.user.service.controller.user.extension;

import cn.kotlincloud.user.common.util.sys.ValueUtils;
import cn.kotlincloud.user.service.controller.base.BaseController;
import cn.kotlincloud.user.service.entity.model.UserType;
import cn.kotlincloud.user.service.services.provider.UserTypeServiceProvider;
import com.jfinal.aop.Inject;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

import static cn.kotlincloud.user.service.config.SerciceConstant.error_msg;

/**
 * Created by yulan on 2019/9/23.
 */
@RequestMapping("/user/type")
public class UserTyepController extends BaseController {
    @Inject
    public UserTypeServiceProvider utsp;
    public  void index(){
        renderText( setTitle( "用户类型！" ) );
    }

    /**
     * 新建用户类型
     */
    public void  save(){
        Integer typeCode = getInt("typeCode");
        Integer userId = getInt("userId");
        String tyepName = get("tyepName");
        Integer appId = getInt("appId");

        UserType userType=new UserType();
        userType.setTypeCode( typeCode );
        userType.setTyepName( tyepName );
        userType.setAppId( appId );
        userType.setCreateUserId( userId );
        userType.setCreateTime( new Date(  ) );
        userType.setUpdateUserId( userId );
        userType.setUpdateTime( new Date(  ) );
        Object save = utsp.save( userType );
        renderJson(isProduceCode( save ));


    }

    /**
     * 查询所有类型
     */
    public void  findAll(){
        List<UserType> userTypeList = utsp.findAll();
        renderJson(isProduceCode( userTypeList ));
    }
    /**
     * 删除类型
     */

    public  void  delById(){
        Integer typeId = getInt( "typeId" );
        if (ValueUtils.isEmpty(typeId)||typeId==0){
            renderText(error_msg);
            return;
        }
        boolean del = utsp.deleteById( typeId );

        renderJson(isProduceCode( del ));
    }

    /**
     * 修改用户类型
     */

    public  void  updateById(){
        Integer typeId = getInt( "typeId" );
        if (ValueUtils.isEmpty(typeId)||typeId==0){
            renderText(error_msg);
            return;
        }
        //用户类型,-1表未激活，0，个人开发者，
        // 1，企业开发者,2普通用户，3，管理员,4,超级管理员,5，6,VIP用户
        Integer typeCode = getInt("typeCode");
        Integer userId = getInt("userId");
        String tyepName = get("tyepName");
        Integer appId = getInt("appId");

        UserType userType=new UserType();
        userType.setId( typeId );
        userType.setTypeCode( typeCode );
        userType.setTyepName( tyepName );
        userType.setAppId( appId );
        userType.setUpdateUserId( userId );
        userType.setUpdateTime( new Date(  ) );
        Object update = utsp.saveOrUpdate( userType );
        renderJson(isProduceCode( update ));
    }

    /**
     * 根据id查询
     */
    public  void  findById(){
        Integer typeId = getInt( "typeId" );
        if (ValueUtils.isEmpty(typeId)||typeId==0){
            renderText(error_msg);
            return;
        }

        UserType userType = utsp.findById( typeId );

        renderJson(isProduceCode( userType ));

    }
    /**
     * 查询某个app的所有用户类型
     */

    public  void  findByAppId(){

        Integer appId = getInt( "appId" );
        if (ValueUtils.isEmpty(appId)||appId==0){
            renderText(error_msg);
            return;
        }

        String sql="SELECT * FROM userType WHERE appId = ? ";
        List<UserType> userTypeList = utsp.find( sql, appId );

        renderJson(isProduceCode( userTypeList ));

    }
}
