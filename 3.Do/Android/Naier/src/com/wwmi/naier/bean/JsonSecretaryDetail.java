package com.wwmi.naier.bean;

public class JsonSecretaryDetail {

    private SecretaryDetail data;
    private String msg;

    public JsonSecretaryDetail(SecretaryDetail data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public SecretaryDetail getData() {
        return data;
    }

    public void setData(SecretaryDetail data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
