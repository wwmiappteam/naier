package com.wwmi.naier.bean;

import java.util.List;

public class JsonActive {

    private String currentPage;
    private String totalPage;
    private List<Active> data;
    private String msg;

    public JsonActive(String currentPage, List<Active> data, String msg,
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

    public List<Active> getData() {
        return data;
    }

    public void setData(List<Active> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Active {
        private String activeID;
        private String activeTitle;
        private String activePoster;
        private String activeStart;
        private String activeEnd;
        private String activeTel;
        private String activeDescription;

        public Active(String activeDescription, String activeEnd,
                String activeID, String activePoster, String activeStart,
                String activeTel, String activeTitle) {
            this.activeDescription = activeDescription;
            this.activeEnd = activeEnd;
            this.activeID = activeID;
            this.activePoster = activePoster;
            this.activeStart = activeStart;
            this.activeTel = activeTel;
            this.activeTitle = activeTitle;
        }

        public String getActiveID() {
            return activeID;
        }

        public void setActiveID(String activeID) {
            this.activeID = activeID;
        }

        public String getActiveTitle() {
            return activeTitle;
        }

        public void setActiveTitle(String activeTitle) {
            this.activeTitle = activeTitle;
        }

        public String getActivePoster() {
            return activePoster;
        }

        public void setActivePoster(String activePoster) {
            this.activePoster = activePoster;
        }

        public String getActiveStart() {
            return activeStart;
        }

        public void setActiveStart(String activeStart) {
            this.activeStart = activeStart;
        }

        public String getActiveEnd() {
            return activeEnd;
        }

        public void setActiveEnd(String activeEnd) {
            this.activeEnd = activeEnd;
        }

        public String getActiveTel() {
            return activeTel;
        }

        public void setActiveTel(String activeTel) {
            this.activeTel = activeTel;
        }

        public String getActiveDescription() {
            return activeDescription;
        }

        public void setActiveDescription(String activeDescription) {
            this.activeDescription = activeDescription;
        }

    }
}
