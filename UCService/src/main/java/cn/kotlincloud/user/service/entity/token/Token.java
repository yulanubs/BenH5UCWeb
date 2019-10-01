package cn.kotlincloud.user.service.entity.token;

import java.io.Serializable;

/**
 * 
	 * @ClassName: Token<BR>
     * @Describe：Token详细信息类<BR>
     * @Author: Jekshow
	 * @Extends：<BR>
     * @Version:1.0 
     * @date:2016-7-28 下午02:16:27
 */
public class Token implements Serializable {
	    
	private static final long serialVersionUID = -4965627051509226289L;
	/**TokenId*/
	private int id;
	/**Token*/
	private String token;
	/**用户id*/
	private int user_id;
	/**openid*/
	private String opne_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOpne_id() {
		return opne_id;
	}
	public void setOpne_id(String opne_id) {
		this.opne_id = opne_id;
	}
	public Token(int id, String token, int user_id, String opne_id) {
		super();
		this.id = id;
		this.token = token;
		this.user_id = user_id;
		this.opne_id = opne_id;
	}
	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Token(String token, int user_id, String opne_id) {
		super();
		this.token = token;
		this.user_id = user_id;
		this.opne_id = opne_id;
	}
	
}
