package com.wwmi.naier.bean;

import java.util.List;

public class JsonKeepersType {

    private List<KeepersType> data;
    private String msg;

    public JsonKeepersType(List<KeepersType> data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public List<KeepersType> getData() {
        return data;
    }

    public void setData(List<KeepersType> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
