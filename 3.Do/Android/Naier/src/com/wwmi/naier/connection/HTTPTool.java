package com.wwmi.naier.connection;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.raontie.util.JsonUtil;
import com.wwmi.naier.bean.JsonActive;
import com.wwmi.naier.bean.JsonBusiness;
import com.wwmi.naier.bean.JsonCompany;
import com.wwmi.naier.bean.JsonKeepersDetail;
import com.wwmi.naier.bean.JsonKeepersType;
import com.wwmi.naier.bean.JsonLogin;
import com.wwmi.naier.bean.JsonMsg;
import com.wwmi.naier.bean.JsonScrollKeepers;
import com.wwmi.naier.bean.JsonSecretary;
import com.wwmi.naier.bean.JsonSecretaryDetail;
import com.wwmi.naier.bean.JsonSecretaryTpyeAndRegion;
import com.wwmi.naier.bean.JsonService;
import com.wwmi.naier.bean.JsonShop;

public class HTTPTool {

    // 服务器地址
    private static final String SERVER_ADDRESS = "http://nell.rxynet.com/interface/";

    // 1. 获取公司信息
    private static final String URL_GET_COMPANY_INFO = SERVER_ADDRESS
            + "GetCompanyInfo.php";
    // 2. 获取私人秘书区域与类型
    private static final String URL_GET_SECRETARY_TYPE_AND_REGION = SERVER_ADDRESS
            + "GetSecretaryTpyeAndRegion.php";
    // 3. 获取私人秘书列表
    private static final String URL_GET_SECRETARY = SERVER_ADDRESS
            + "GetSecretary.php";
    // 4. 获取私人秘书详细
    private static final String URL_GET_SECRETARY_DETAIL = SERVER_ADDRESS
            + "GetSecretaryDetail.php";
    // 5. 获取走马灯家政人员列表
    private static final String URL_GET_SCROLL_KEEPERS = SERVER_ADDRESS
            + "GetScrollKeepers.php";
    // 6. 获取家政人员分类与人员列表(6个分类)
    private static final String URL_GET_KEEPERS_TYPE = SERVER_ADDRESS
            + "GetKeepersType.php";
    // 7. 获取家政人员详细
    private static final String URL_GET_KEEPERS_DETAIL = SERVER_ADDRESS
            + "GetKeepersDetail.php";
    // 8. 获取核心业务列表
    private static final String URL_GET_BUSINESS = SERVER_ADDRESS
            + "GetBusiness.php";
    // 9. 获取服务介绍列表
    private static final String URL_GET_SERVICE = SERVER_ADDRESS + "GetService.php";
    // 10. 获取新闻活动列表
    private static final String URL_GET_ACTIVE = SERVER_ADDRESS + "GetActive.php";
    // 11. 获取门市列表
    private static final String URL_GET_SHOP = SERVER_ADDRESS + "GetShop.php";
    // 12. 顾客登录
    private static final String URL_LOGIN = SERVER_ADDRESS + "Login.php";
    // 13. 顾客注册
    private static final String URL_REGISTER = SERVER_ADDRESS + "Register.php";
    // 14. 更新个人信息
    private static final String URL_USERINFO_MODIFY = SERVER_ADDRESS
            + "UserinfoModify.php";
    // 15. 添加预约
    private static final String URL_KEEPER_ORDER_ADD = SERVER_ADDRESS
            + "KeeperOrderAdd.php";
    // 16. 添加投诉
    private static final String URL_COMPLAIN_ADD = SERVER_ADDRESS
            + "ComplainAdd.php";
    // 17. 意见反馈
    private static final String URL_ADVISE_ADD = SERVER_ADDRESS + "AdviseAdd.php";

    public static JsonCompany getCompanyInfo() {
        String strResult = HTTPHelper.doPost(URL_GET_COMPANY_INFO, null);
        if (strResult == null)
            return null;
        JsonCompany jsonCompany = null;
        try {
            jsonCompany = (JsonCompany) JsonUtil.decode(JsonCompany.class,
                    new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonCompany;
    }

    public static JsonSecretaryTpyeAndRegion getSecretaryTpyeAndRegion() {
        String strResult = HTTPHelper.doPost(URL_GET_SECRETARY_TYPE_AND_REGION,
                null);
        if (strResult == null)
            return null;
        JsonSecretaryTpyeAndRegion jsonSecretaryTpyeAndRegion = null;
        try {
            jsonSecretaryTpyeAndRegion = (JsonSecretaryTpyeAndRegion) JsonUtil
                    .decode(JsonSecretaryTpyeAndRegion.class, new JSONObject(
                            strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonSecretaryTpyeAndRegion;
    }

    public static JsonSecretary getSecretary(String currentPage,
            String pageRows, String typeID, String regionID, String title) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("currentPage", currentPage);
        map.put("pageRows", pageRows);
        map.put("typeID", typeID == null ? "" : typeID);
        map.put("regionID", regionID == null ? "" : regionID);
        map.put("title", title == null ? "" : title);
        String strResult = HTTPHelper.doPost(URL_GET_SECRETARY, map);
        if (strResult == null)
            return null;
        JsonSecretary jsonSecretary = null;
        try {
            jsonSecretary = (JsonSecretary) JsonUtil.decode(
                    JsonSecretary.class, new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonSecretary;
    }

    public static JsonSecretaryDetail getSecretaryDetail(String secretaryID) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("secretaryID", secretaryID);
        String strResult = HTTPHelper.doPost(URL_GET_SECRETARY_DETAIL, map);
        if (strResult == null)
            return null;
        JsonSecretaryDetail jsonSecretaryDetail = null;
        try {
            jsonSecretaryDetail = (JsonSecretaryDetail) JsonUtil.decode(
                    JsonSecretaryDetail.class, new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonSecretaryDetail;
    }

    public static JsonScrollKeepers getScrollKeepers() {
        String strResult = HTTPHelper.doPost(URL_GET_SCROLL_KEEPERS, null);
        if (strResult == null)
            return null;
        JsonScrollKeepers jsonScrollKeepers = null;
        try {
            jsonScrollKeepers = (JsonScrollKeepers) JsonUtil.decode(
                    JsonScrollKeepers.class, new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonScrollKeepers;
    }

    public static JsonKeepersType getKeepersType() {
        String strResult = HTTPHelper.doPost(URL_GET_KEEPERS_TYPE, null);
        if (strResult == null)
            return null;
        JsonKeepersType jsonKeepersType = null;
        try {
            jsonKeepersType = (JsonKeepersType) JsonUtil.decode(
                    JsonKeepersType.class, new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonKeepersType;
    }

    public static JsonKeepersDetail getKeepersDetail(String keeperID) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("keeperID", keeperID);
        String strResult = HTTPHelper.doPost(URL_GET_KEEPERS_DETAIL, map);
        if (strResult == null)
            return null;
        JsonKeepersDetail jsonKeepersDetail = null;
        try {
            jsonKeepersDetail = (JsonKeepersDetail) JsonUtil.decode(
                    JsonKeepersDetail.class, new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonKeepersDetail;
    }

    public static JsonBusiness getBusiness(String currentPage, String pageRows) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("currentPage", currentPage);
        map.put("pageRows", pageRows);
        String strResult = HTTPHelper.doPost(URL_GET_BUSINESS, map);
        if (strResult == null)
            return null;
        JsonBusiness jsonBusiness = null;
        try {
            jsonBusiness = (JsonBusiness) JsonUtil.decode(JsonBusiness.class,
                    new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonBusiness;
    }

    public static JsonService getService(String currentPage, String pageRows) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("currentPage", currentPage);
        map.put("pageRows", pageRows);
        String strResult = HTTPHelper.doPost(URL_GET_SERVICE, map);
        if (strResult == null)
            return null;
        JsonService jsonService = null;
        try {
            jsonService = (JsonService) JsonUtil.decode(JsonService.class,
                    new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonService;
    }

    public static JsonActive getActive(String currentPage, String pageRows) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("currentPage", currentPage);
        map.put("pageRows", pageRows);
        String strResult = HTTPHelper.doPost(URL_GET_ACTIVE, map);
        if (strResult == null)
            return null;
        JsonActive jsonActive = null;
        try {
            jsonActive = (JsonActive) JsonUtil.decode(JsonActive.class,
                    new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonActive;
    }

    public static JsonShop getShop(String currentPage, String pageRows) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("currentPage", currentPage);
        map.put("pageRows", pageRows);
        String strResult = HTTPHelper.doPost(URL_GET_SHOP, map);
        if (strResult == null)
            return null;
        JsonShop jsonShop = null;
        try {
            jsonShop = (JsonShop) JsonUtil.decode(JsonShop.class,
                    new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonShop;
    }

    public static JsonLogin login(String username, String password) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        String strResult = HTTPHelper.doPost(URL_LOGIN, map);
        if (strResult == null)
            return null;
        JsonLogin jsonLogin = null;
        try {
            jsonLogin = (JsonLogin) JsonUtil.decode(JsonLogin.class,
                    new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonLogin;
    }

    public static JsonLogin register(String username, String password,
            String name, String cellphone, String address) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        map.put("name", name);
        map.put("cellphone", cellphone);
        map.put("address", address);
        String strResult = HTTPHelper.doPost(URL_REGISTER, map);
        if (strResult == null)
            return null;
        JsonLogin jsonLogin = null;
        try {
            jsonLogin = (JsonLogin) JsonUtil.decode(JsonLogin.class,
                    new JSONObject(strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonLogin;
    }

    public static JsonMsg userinfoModify(String customID, String cellphone,
            String address, String passwordInitial, String passwordNew) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("customID", customID);
        map.put("cellphone", cellphone == null ? "" : cellphone);
        map.put("address", address == null ? "" : address);
        map.put("passwordInitial", passwordInitial == null ? ""
                : passwordInitial);
        map.put("passwordNew", passwordNew == null ? "" : passwordNew);
        String strResult = HTTPHelper.doPost(URL_USERINFO_MODIFY, map);
        if (strResult == null)
            return null;
        JsonMsg jsonMsg = null;
        try {
            jsonMsg = (JsonMsg) JsonUtil.decode(JsonMsg.class, new JSONObject(
                    strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMsg;
    }

    public static JsonMsg keeperOrderAdd(String customID, String keeperID,
            String startTime, String endTime, String orderDescription) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("customID", customID);
        map.put("keeperID", keeperID);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("orderDescription", orderDescription);
        String strResult = HTTPHelper.doPost(URL_KEEPER_ORDER_ADD, map);
        if (strResult == null)
            return null;
        JsonMsg jsonMsg = null;
        try {
            jsonMsg = (JsonMsg) JsonUtil.decode(JsonMsg.class, new JSONObject(
                    strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMsg;
    }

    public static JsonMsg complainAdd(String customID, String keeperID,
            String complainContent) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("customID", customID);
        map.put("keeperID", keeperID);
        map.put("complainContent", complainContent);
        String strResult = HTTPHelper.doPost(URL_COMPLAIN_ADD, map);
        if (strResult == null)
            return null;
        JsonMsg jsonMsg = null;
        try {
            jsonMsg = (JsonMsg) JsonUtil.decode(JsonMsg.class, new JSONObject(
                    strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMsg;
    }

    public static JsonMsg adviseAdd(String customID, String cellphone,
            String adviseContent) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("customID", customID == null ? "" : customID);
        map.put("cellphone", cellphone);
        map.put("adviseContent", adviseContent);
        String strResult = HTTPHelper.doPost(URL_ADVISE_ADD, map);
        if (strResult == null)
            return null;
        JsonMsg jsonMsg = null;
        try {
            jsonMsg = (JsonMsg) JsonUtil.decode(JsonMsg.class, new JSONObject(
                    strResult));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMsg;
    }
}
