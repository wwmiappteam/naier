package com.wwmi.naier.bean;

import java.util.List;

public class Company {
    private List<String> picturesList;
    private String advise_tel;
    private String complain_tel;
    private String secretary_tel;
    private String keeper_tel;
    private String address;
    private String email;
    private String qq;
    private String about;

    public Company(String about, String address, String advise_tel,
            String complain_tel, String email, String keeper_tel,
            List<String> picturesList, String qq, String secretary_tel) {
        this.about = about;
        this.address = address;
        this.advise_tel = advise_tel;
        this.complain_tel = complain_tel;
        this.email = email;
        this.keeper_tel = keeper_tel;
        this.picturesList = picturesList;
        this.qq = qq;
        this.secretary_tel = secretary_tel;
    }

    public List<String> getPicturesList() {
        return picturesList;
    }

    public void setPicturesList(List<String> picturesList) {
        this.picturesList = picturesList;
    }

    public String getAdvise_tel() {
        return advise_tel;
    }

    public void setAdvise_tel(String advise_tel) {
        this.advise_tel = advise_tel;
    }

    public String getComplain_tel() {
        return complain_tel;
    }

    public void setComplain_tel(String complain_tel) {
        this.complain_tel = complain_tel;
    }

    public String getSecretary_tel() {
        return secretary_tel;
    }

    public void setSecretary_tel(String secretary_tel) {
        this.secretary_tel = secretary_tel;
    }

    public String getKeeper_tel() {
        return keeper_tel;
    }

    public void setKeeper_tel(String keeper_tel) {
        this.keeper_tel = keeper_tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
