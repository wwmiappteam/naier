package com.wwmi.naier.bean;

import java.util.List;

public class JsonShop {
    private String currentPage;
    private String totalPage;
    private List<Shop> data;
    private String msg;

    public JsonShop(String currentPage, List<Shop> data, String msg,
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

    public List<Shop> getData() {
        return data;
    }

    public void setData(List<Shop> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Shop {
        private String shopID;
        private String shopName;
        private String shopTel;
        private String shopAddress;
        private String baiduLongitude;
        private String baiduLatitude;
        private String googleLongitude;
        private String googleLatitude;

        public Shop(String baiduLatitude, String baiduLongitude,
                String googleLatitude, String googleLongitude,
                String shopAddress, String shopID, String shopName,
                String shopTel) {
            this.baiduLatitude = baiduLatitude;
            this.baiduLongitude = baiduLongitude;
            this.googleLatitude = googleLatitude;
            this.googleLongitude = googleLongitude;
            this.shopAddress = shopAddress;
            this.shopID = shopID;
            this.shopName = shopName;
            this.shopTel = shopTel;
        }

        public String getShopID() {
            return shopID;
        }

        public void setShopID(String shopID) {
            this.shopID = shopID;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopTel() {
            return shopTel;
        }

        public void setShopTel(String shopTel) {
            this.shopTel = shopTel;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getBaiduLongitude() {
            return baiduLongitude;
        }

        public void setBaiduLongitude(String baiduLongitude) {
            this.baiduLongitude = baiduLongitude;
        }

        public String getBaiduLatitude() {
            return baiduLatitude;
        }

        public void setBaiduLatitude(String baiduLatitude) {
            this.baiduLatitude = baiduLatitude;
        }

        public String getGoogleLongitude() {
            return googleLongitude;
        }

        public void setGoogleLongitude(String googleLongitude) {
            this.googleLongitude = googleLongitude;
        }

        public String getGoogleLatitude() {
            return googleLatitude;
        }

        public void setGoogleLatitude(String googleLatitude) {
            this.googleLatitude = googleLatitude;
        }

    }
}
