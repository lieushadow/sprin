package com.djdg.zzkg.supply.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * SampleUser:刘敏华 shadow.liu@hey900.com
 * DateDeserializer: 2017-08-08
 * Time: 14:05
 */
public enum ErrCode {

    LOGIN_FAILD(401,"登录超时"),
    SERVER_ERR(500,"服务器繁忙，请稍后再试"),
    METHOD_ERR(500,"请求方法不正确");

    private int code;
    private String msg;

    ErrCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getCodeMsg() {
        return code + ":" + msg ;
    }
}
