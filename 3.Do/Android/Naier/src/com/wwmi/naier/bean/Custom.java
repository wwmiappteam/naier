package com.wwmi.naier.bean;

import java.util.List;

public class Custom {
    private String customID;
    private String userName;
    private String password;
    private String cellphone;
    private String name;
    private String address;
    private List<Order> orderList;

    public Custom(String address, String cellphone, String customID,
            String name, List<Order> orderList, String password, String userName) {
        this.address = address;
        this.cellphone = cellphone;
        this.customID = customID;
        this.name = name;
        this.orderList = orderList;
        this.password = password;
        this.userName = userName;
    }

    public String getCustomID() {
        return customID;
    }

    public void setCustomID(String customID) {
        this.customID = customID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public static class Order {

        private String orderID;
        private String keeperID;
        private String keeperName;
        private String keeperTypeDescription;
        private String keeperPhoto;
        private String startTime;
        private String endTime;

        public Order(String endTime, String keeperID, String keeperName,
                String keeperPhoto, String keeperTypeDescription,
                String orderID, String startTime) {
            this.endTime = endTime;
            this.keeperID = keeperID;
            this.keeperName = keeperName;
            this.keeperPhoto = keeperPhoto;
            this.keeperTypeDescription = keeperTypeDescription;
            this.orderID = orderID;
            this.startTime = startTime;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getKeeperID() {
            return keeperID;
        }

        public void setKeeperID(String keeperID) {
            this.keeperID = keeperID;
        }

        public String getKeeperName() {
            return keeperName;
        }

        public void setKeeperName(String keeperName) {
            this.keeperName = keeperName;
        }

        public String getKeeperTypeDescription() {
            return keeperTypeDescription;
        }

        public void setKeeperTypeDescription(String keeperTypeDescription) {
            this.keeperTypeDescription = keeperTypeDescription;
        }

        public String getKeeperPhoto() {
            return keeperPhoto;
        }

        public void setKeeperPhoto(String keeperPhoto) {
            this.keeperPhoto = keeperPhoto;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

    }
}
