package cn.kotlincloud.user.service.entity.model;

import cn.kotlincloud.user.service.entity.wx.WXLoginInfo;

/**
 * Created by yulan on 2019/9/21.
 */
public class Users  {
    private  String userToken;
    private  Userinfo userInfo;
    private WXLoginInfo wxLoginInfo;

    public Users(String userToken, Userinfo userInfo, WXLoginInfo wxLoginInfo) {
        this.userToken = userToken;
        this.userInfo = userInfo;
        this.wxLoginInfo = wxLoginInfo;
    }

    public Users() {

    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Userinfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Userinfo userInfo) {
        this.userInfo = userInfo;
    }

    public WXLoginInfo getWxLoginInfo() {
        return wxLoginInfo;
    }

    public void setWxLoginInfo(WXLoginInfo wxLoginInfo) {
        this.wxLoginInfo = wxLoginInfo;
    }
}
