package com.wwmi.naier.bean;

import java.util.List;

public class SecretaryTpyeFather {

    private String typeID;
    private String typeName;
    private List<SecretaryTpye> childTypeList;

    public SecretaryTpyeFather(List<SecretaryTpye> childTypeList,
            String typeID, String typeName) {
        this.childTypeList = childTypeList;
        this.typeID = typeID;
        this.typeName = typeName;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<SecretaryTpye> getChildTypeList() {
        return childTypeList;
    }

    public void setChildTypeList(List<SecretaryTpye> childTypeList) {
        this.childTypeList = childTypeList;
    }

}
