package cn.kotlincloud.user.service.entity.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserPwd<M extends BaseUserPwd<M>> extends JbootModel<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setPwdCode(java.lang.String pwdCode) {
		set("pwdCode", pwdCode);
	}
	
	public java.lang.String getPwdCode() {
		return getStr("pwdCode");
	}

	public void setUserCode(java.lang.String userCode) {
		set("userCode", userCode);
	}
	
	public java.lang.String getUserCode() {
		return getStr("userCode");
	}

	public void setPwd(java.lang.String pwd) {
		set("pwd", pwd);
	}
	
	public java.lang.String getPwd() {
		return getStr("pwd");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("updateTime", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("updateTime");
	}

}