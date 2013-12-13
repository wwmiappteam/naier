package com.wwmi.naier.bean;

import java.util.List;

public class JsonService {

    private String currentPage;
    private String totalPage;
    private List<Service> data;
    private String msg;

    public JsonService(String currentPage, List<Service> data, String msg,
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

    public List<Service> getData() {
        return data;
    }

    public void setData(List<Service> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Service {
        private String serviceID;
        private String servTitle;
        private String servIntroduce;

        public Service(String servIntroduce, String servTitle, String serviceID) {
            this.servIntroduce = servIntroduce;
            this.servTitle = servTitle;
            this.serviceID = serviceID;
        }

        public String getServiceID() {
            return serviceID;
        }

        public void setServiceID(String serviceID) {
            this.serviceID = serviceID;
        }

        public String getServTitle() {
            return servTitle;
        }

        public void setServTitle(String servTitle) {
            this.servTitle = servTitle;
        }

        public String getServIntroduce() {
            return servIntroduce;
        }

        public void setServIntroduce(String servIntroduce) {
            this.servIntroduce = servIntroduce;
        }
    }
}
