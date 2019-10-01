/**
  * Copyright 2019 bejson.com 
  */
package cn.kotlincloud.user.service.entity.wx;

import java.io.Serializable;

/**
 * Auto-generated: 2019-09-27 15:14:41
 *
 * 微信用户信息
 */
public class WxUserInfo   implements Serializable {

    private String nickName;
    private int gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    public void setNickName(String nickName) {
         this.nickName = nickName;
     }
     public String getNickName() {
         return nickName;
     }

    public WxUserInfo(String nickName, int gender, String language, String city, String province, String country, String avatarUrl) {
        this.nickName = nickName;
        this.gender = gender;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.avatarUrl = avatarUrl;
    }

    public void setGender(int gender) {
         this.gender = gender;
     }
     public int getGender() {
         return gender;
     }

    public void setLanguage(String language) {
         this.language = language;
     }
     public String getLanguage() {
         return language;
     }

    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setProvince(String province) {
         this.province = province;
     }
     public String getProvince() {
         return province;
     }

    public void setCountry(String country) {
         this.country = country;
     }
     public String getCountry() {
         return country;
     }

    public void setAvatarUrl(String avatarUrl) {
         this.avatarUrl = avatarUrl;
     }
     public String getAvatarUrl() {
         return avatarUrl;
     }

}