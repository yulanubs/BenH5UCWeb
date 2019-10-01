package cn.kotlincloud.user.common.model.json;

/**
 * Created by yulan on 2019/8/16.
 */
public class JsonBean<T>{
    public  String respCode="0000";
    public  String respMsg="成功";
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }
}
