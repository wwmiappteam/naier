package com.wwmi.naier.bean;

import java.util.List;

public class JsonScrollKeepers {
    private List<ScrollKeepers> data;
    private String msg;

    public JsonScrollKeepers(List<ScrollKeepers> data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public List<ScrollKeepers> getData() {
        return data;
    }

    public void setData(List<ScrollKeepers> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ScrollKeepers {

        private String keeperID;
        private String keeperPhoto;

        public ScrollKeepers(String keeperID, String keeperPhoto) {
            this.keeperID = keeperID;
            this.keeperPhoto = keeperPhoto;
        }

        public String getKeeperID() {
            return keeperID;
        }

        public void setKeeperID(String keeperID) {
            this.keeperID = keeperID;
        }

        public String getKeeperPhoto() {
            return keeperPhoto;
        }

        public void setKeeperPhoto(String keeperPhoto) {
            this.keeperPhoto = keeperPhoto;
        }
    }

	/**
	 * Description : 
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "JsonScrollKeepers [data=" + data + ", msg=" + msg + "]";
	}
    
    
}
