package com.wwmi.naier.bean;

import java.util.List;

public class JsonSecretaryTpyeAndRegion {

    private SecretaryData data;
    private String msg;

    public JsonSecretaryTpyeAndRegion(SecretaryData data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public SecretaryData getData() {
        return data;
    }

    public void setData(SecretaryData data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class SecretaryData {
        private List<SecretaryTpyeFather> typeList;
        private List<SecretaryRegion> regionList;

        public SecretaryData(List<SecretaryRegion> regionList,
                List<SecretaryTpyeFather> typeList) {
            this.regionList = regionList;
            this.typeList = typeList;
        }

        public List<SecretaryTpyeFather> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<SecretaryTpyeFather> typeList) {
            this.typeList = typeList;
        }

        public List<SecretaryRegion> getRegionList() {
            return regionList;
        }

        public void setRegionList(List<SecretaryRegion> regionList) {
            this.regionList = regionList;
        }
    }
}
