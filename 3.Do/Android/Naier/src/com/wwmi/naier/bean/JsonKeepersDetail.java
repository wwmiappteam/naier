package com.wwmi.naier.bean;

public class JsonKeepersDetail {

    private KeepersDetail data;
    private String msg;

    public JsonKeepersDetail(KeepersDetail data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public KeepersDetail getData() {
        return data;
    }

    public void setData(KeepersDetail data) {
        this.data = data;
    }

    /**
	 * Description : 
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "JsonKeepersDetail [data=" + data + ", msg=" + msg + "]";
	}

	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
