package com.wwmi.naier.bean;

public class Secretary {

    private String secretaryID;
    private String typeName;
    private String regionName;
    private String title;
    private String address;

    public Secretary(String address, String regionName, String secretaryID,
            String title, String typeName) {
        this.address = address;
        this.regionName = regionName;
        this.secretaryID = secretaryID;
        this.title = title;
        this.typeName = typeName;
    }

    public String getSecretaryID() {
        return secretaryID;
    }

    public void setSecretaryID(String secretaryID) {
        this.secretaryID = secretaryID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
