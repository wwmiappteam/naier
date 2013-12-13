package com.wwmi.naier.bean;

public class SecretaryRegion {

    private String regionID;
    private String regionName;

    public SecretaryRegion(String regionID, String regionName) {
        this.regionID = regionID;
        this.regionName = regionName;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

}
