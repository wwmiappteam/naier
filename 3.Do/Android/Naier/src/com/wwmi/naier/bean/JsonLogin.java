package com.wwmi.naier.bean;

public class JsonLogin {

    private Custom data;
    private String msg;

    public JsonLogin(Custom data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public Custom getData() {
        return data;
    }

    public void setData(Custom data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
