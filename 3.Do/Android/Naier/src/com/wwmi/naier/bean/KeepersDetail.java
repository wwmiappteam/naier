package com.wwmi.naier.bean;

public class KeepersDetail {

    private String keeperID;
    private String typeName;
    private String keeper_name;
    private String keeper_gender;
    private String keeper_age;
    private String keeper_photo;
    private String keeper_experience;
    private String keeper_level;
    private String keeper_professional;
    private String keeper_attitude;
    private String keeper_hardworking;
    private String keeper_attentive;
    private String keeper_special;
    private String keeper_introduce;

    public KeepersDetail(String keeperID, String keeper_age,
            String keeper_attentive, String keeper_attitude,
            String keeper_experience, String keeper_gender,
            String keeper_hardworking, String keeper_introduce,
            String keeper_level, String keeper_name, String keeper_photo,
            String keeper_professional, String keeper_special, String typeName) {
        this.keeperID = keeperID;
        this.keeper_age = keeper_age;
        this.keeper_attentive = keeper_attentive;
        this.keeper_attitude = keeper_attitude;
        this.keeper_experience = keeper_experience;
        this.keeper_gender = keeper_gender;
        this.keeper_hardworking = keeper_hardworking;
        this.keeper_introduce = keeper_introduce;
        this.keeper_level = keeper_level;
        this.keeper_name = keeper_name;
        this.keeper_photo = keeper_photo;
        this.keeper_professional = keeper_professional;
        this.keeper_special = keeper_special;
        this.typeName = typeName;
    }

    public String getKeeperID() {
        return keeperID;
    }

    public void setKeeperID(String keeperID) {
        this.keeperID = keeperID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getKeeper_name() {
        return keeper_name;
    }

    public void setKeeper_name(String keeper_name) {
        this.keeper_name = keeper_name;
    }

    public String getKeeper_gender() {
        return keeper_gender;
    }

    public void setKeeper_gender(String keeper_gender) {
        this.keeper_gender = keeper_gender;
    }

    public String getKeeper_age() {
        return keeper_age;
    }

    public void setKeeper_age(String keeper_age) {
        this.keeper_age = keeper_age;
    }

    public String getKeeper_photo() {
        return keeper_photo;
    }

    public void setKeeper_photo(String keeper_photo) {
        this.keeper_photo = keeper_photo;
    }

    public String getKeeper_experience() {
        return keeper_experience;
    }

    public void setKeeper_experience(String keeper_experience) {
        this.keeper_experience = keeper_experience;
    }

    public String getKeeper_level() {
        return keeper_level;
    }

    public void setKeeper_level(String keeper_level) {
        this.keeper_level = keeper_level;
    }

    public String getKeeper_professional() {
        return keeper_professional;
    }

    public void setKeeper_professional(String keeper_professional) {
        this.keeper_professional = keeper_professional;
    }

    public String getKeeper_attitude() {
        return keeper_attitude;
    }

    public void setKeeper_attitude(String keeper_attitude) {
        this.keeper_attitude = keeper_attitude;
    }

    public String getKeeper_hardworking() {
        return keeper_hardworking;
    }

    public void setKeeper_hardworking(String keeper_hardworking) {
        this.keeper_hardworking = keeper_hardworking;
    }

    public String getKeeper_attentive() {
        return keeper_attentive;
    }

    public void setKeeper_attentive(String keeper_attentive) {
        this.keeper_attentive = keeper_attentive;
    }

    public String getKeeper_special() {
        return keeper_special;
    }

    public void setKeeper_special(String keeper_special) {
        this.keeper_special = keeper_special;
    }

    public String getKeeper_introduce() {
        return keeper_introduce;
    }

    public void setKeeper_introduce(String keeper_introduce) {
        this.keeper_introduce = keeper_introduce;
    }

	/**
	 * Description : 
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "KeepersDetail [keeperID=" + keeperID + ", typeName=" + typeName + ", keeper_name=" + keeper_name
				+ ", keeper_gender=" + keeper_gender + ", keeper_age=" + keeper_age + ", keeper_photo=" + keeper_photo
				+ ", keeper_experience=" + keeper_experience + ", keeper_level=" + keeper_level
				+ ", keeper_professional=" + keeper_professional + ", keeper_attitude=" + keeper_attitude
				+ ", keeper_hardworking=" + keeper_hardworking + ", keeper_attentive=" + keeper_attentive
				+ ", keeper_special=" + keeper_special + ", keeper_introduce=" + keeper_introduce + "]";
	}

}
