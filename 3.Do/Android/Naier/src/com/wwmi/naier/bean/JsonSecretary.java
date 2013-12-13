package com.wwmi.naier.bean;

import java.util.List;

public class JsonSecretary {

    private String currentPage;
    private String totalPage;
    private List<Secretary> data;
    private String msg;

    public JsonSecretary(String currentPage, List<Secretary> data, String msg,
            String totalPage) {
        this.currentPage = currentPage;
        this.data = data;
        this.msg = msg;
        this.totalPage = totalPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<Secretary> getData() {
        return data;
    }

    public void setData(List<Secretary> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
