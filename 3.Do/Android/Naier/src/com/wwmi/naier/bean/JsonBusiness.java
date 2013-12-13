package com.wwmi.naier.bean;

import java.util.List;

public class JsonBusiness {
    private String currentPage;
    private String totalPage;
    private List<Business> data;
    private String msg;

    public JsonBusiness(String currentPage, List<Business> data, String msg,
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

    public List<Business> getData() {
        return data;
    }

    public void setData(List<Business> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Business {

        private String businessID;
        private String busiTitle;
        private String busiPrice;
        private String busiIntroduce;

        public Business(String busiIntroduce, String busiPrice,
                String busiTitle, String businessID) {
            this.busiIntroduce = busiIntroduce;
            this.busiPrice = busiPrice;
            this.busiTitle = busiTitle;
            this.businessID = businessID;
        }

        public String getBusinessID() {
            return businessID;
        }

        public void setBusinessID(String businessID) {
            this.businessID = businessID;
        }

        public String getBusiTitle() {
            return busiTitle;
        }

        public void setBusiTitle(String busiTitle) {
            this.busiTitle = busiTitle;
        }

        public String getBusiPrice() {
            return busiPrice;
        }

        public void setBusiPrice(String busiPrice) {
            this.busiPrice = busiPrice;
        }

        public String getBusiIntroduce() {
            return busiIntroduce;
        }

        public void setBusiIntroduce(String busiIntroduce) {
            this.busiIntroduce = busiIntroduce;
        }

    }
}
