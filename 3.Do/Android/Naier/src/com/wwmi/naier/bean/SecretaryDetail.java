package com.wwmi.naier.bean;

public class SecretaryDetail {

    private String secretaryID;
    private String typeName;
    private String regionName;
    private String title;
    private String address;
    private String tel;
    private String special;
    private String price;
    private String images;
    private String description;

    public SecretaryDetail(String address, String description, String images,
            String price, String regionName, String secretaryID,
            String special, String tel, String title, String typeName) {
        this.address = address;
        this.description = description;
        this.images = images;
        this.price = price;
        this.regionName = regionName;
        this.secretaryID = secretaryID;
        this.special = special;
        this.tel = tel;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
