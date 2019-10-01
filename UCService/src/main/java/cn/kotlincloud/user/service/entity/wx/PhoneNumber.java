package cn.kotlincloud.user.service.entity.wx;

import java.io.Serializable;

/**
 * Created by yulan on 2019/9/25.
 */
public class PhoneNumber   implements Serializable {
    private  String phoneNumber;
    private  String purePhoneNumber;
    private  String countryCode;
    private Watermark watermark;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurePhoneNumber() {
        return purePhoneNumber;
    }

    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }

    public PhoneNumber(String phoneNumber, String purePhoneNumber, String countryCode, Watermark watermark) {
        this.phoneNumber = phoneNumber;
        this.purePhoneNumber = purePhoneNumber;
        this.countryCode = countryCode;
        this.watermark = watermark;
    }

    /**
     *
     */
    public  class  Watermark{
        private  String timestamp;
        private  String wx60c901bbcc85561b;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getWx60c901bbcc85561b() {
            return wx60c901bbcc85561b;
        }

        public void setWx60c901bbcc85561b(String wx60c901bbcc85561b) {
            this.wx60c901bbcc85561b = wx60c901bbcc85561b;
        }

        public Watermark(String timestamp, String wx60c901bbcc85561b) {
            this.timestamp = timestamp;
            this.wx60c901bbcc85561b = wx60c901bbcc85561b;
        }
    }
}
