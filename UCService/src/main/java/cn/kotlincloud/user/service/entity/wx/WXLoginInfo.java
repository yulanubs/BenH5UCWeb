package cn.kotlincloud.user.service.entity.wx;

import java.io.Serializable;

/**
 * Created by yulan on 2019/9/24.
 */
public class WXLoginInfo   implements Serializable {
    private   String session_key;
    private   String openid;

    public WXLoginInfo(String session_key, String openid) {
        this.session_key = session_key;
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
