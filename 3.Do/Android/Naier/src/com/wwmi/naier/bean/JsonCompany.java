package com.wwmi.naier.bean;

public class JsonCompany {
    private Company data;
    private String msg;

    public JsonCompany(Company data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public Company getData() {
        return data;
    }

    public void setData(Company data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
