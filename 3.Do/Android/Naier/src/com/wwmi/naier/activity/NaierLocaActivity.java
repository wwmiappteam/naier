/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MKMapTouchListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonShop;
import com.wwmi.naier.bean.JsonShop.Shop;
import com.wwmi.naier.connection.HTTPTool;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午04:34:25
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierLocaActivity extends NaierBaseActivity implements
        OnClickListener, MKMapTouchListener {
    private ArrayList<JsonShop.Shop> shopsList;
    private NaierApplication application;
    /**
     * 百度地图mMapView
     */
    private MapView mMapView;

    /**
     * 坐标点list
     */
    private ArrayList<GeoPoint> geoPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (NaierApplication) getApplication();
        application.initBmapManager(this);
        setContentView(R.layout.naier_loca_layout);
        mMapView = (MapView) findViewById(R.id.map_view);
        new LoadingShops().execute(0);
        application = (NaierApplication) getApplication();
        initTopBaseViews("位置服务", false, true, false, R.drawable.call_consultant);
    }

    /**
     * Description:加载百度地图，设置缩放大小，设置控制工具
     * 
     * @param mMapView
     */
    public void loadBaiduMap(MapView mMapView) {
        // Toast.makeText(activity, "正在加载地图...", Toast.LENGTH_LONG).show();

        // 设置启用内置的缩放控件
        MapController mMapController = mMapView.getController();
        GeoPoint point;
        if (shopsList != null && shopsList.size() > 0) {
            point = new GeoPoint(
                    (int) (Double.parseDouble(shopsList.get(0).getBaiduLatitude()) * 1E6),
                    (int) (Double.parseDouble(shopsList.get(0).getBaiduLongitude()) * 1E6));
        } else {
            // 成都市中心
            point = new GeoPoint((int) (30.658875731053246 * 1E6),
                    (int) (104.06344702579499 * 1E6));
        }
        mMapController.setCenter(point);
        mMapController.setZoom(12);
        // 先确定要几个overlay
        initOverlay();
        mMapView.refresh();

    }

    /**
     * Description:初始化overlay
     * 
     */
    private void initOverlay() {
        geoPoints = new ArrayList<GeoPoint>();
        Overlayer overlay = new Overlayer(getResources().getDrawable(
                R.drawable.icon_gcoding), mMapView);
        for (int i = 0; i < shopsList.size(); i++) {
            // 创建个overlay
            GeoPoint point = new GeoPoint((int) (Float.parseFloat(shopsList
                    .get(i).getBaiduLatitude()) * 1E6),
                    (int) (Float.parseFloat(shopsList.get(i)
                            .getBaiduLongitude()) * 1E6));
            geoPoints.add(point);
            OverlayItem item = new OverlayItem(point, " ", "");
            item.setMarker(getResources().getDrawable(R.drawable.icon_gcoding));
            overlay.addItem(item);
        }
        mMapView.getOverlays().add(overlay);
    }

    class Overlayer extends ItemizedOverlay<OverlayItem> {

        @Override
        protected boolean onTap(int arg0) {
            showPopup(arg0);
            return super.onTap(arg0);
        }

        public Overlayer(Drawable arg0, MapView arg1) {
            super(arg0, arg1);
        }

    }

    /**
     * Description:显示弹出框
     * 
     */
    private void showPopup(int index) {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.map_overlay, null);
        TextView txtName = (TextView) view
                .findViewById(R.id.txt_overlay_shopname);
        TextView txtTele = (TextView) view
                .findViewById(R.id.txt_overlay_shoptelephone);
        TextView txtAddress = (TextView) view
                .findViewById(R.id.txt_overlay_shopaddress);
        txtName.setText("体验店:" + shopsList.get(index).getShopName());
        txtTele.setText("电话:" + shopsList.get(index).getShopTel());
        txtAddress.setText("地址:" + shopsList.get(index).getShopAddress());

        pop = new PopupOverlay(mMapView, new PopupClickListener() {

            @Override
            public void onClickedPopup(int arg0) {
                pop.hidePop();
            }
        });
        pop.showPopup(view, geoPoints.get(index), 1);
    }

    private PopupOverlay pop;

    /**
     * @author XiongWeimin
     * @Description 覆盖物子类
     * @revise
     * @time 2013-7-31 下午04:44:37
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    @SuppressWarnings("rawtypes")
    public static class Overlay extends ItemizedOverlay {

        public Overlay(Drawable defaultMarker, MapView mapView) {
            super(defaultMarker, mapView);
        }
    }

    /**
     * @author XiongWeimin 获取门市列表
     * @Description
     * @revise
     * @time 2013-11-25 下午04:22:28
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class LoadingShops extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPostExecute(Integer result) {
            loadBaiduMap(mMapView);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            if (HTTPTool.getShop("1", "100") == null) {

            } else {
                shopsList = (ArrayList<Shop>) HTTPTool.getShop("1", "100")
                        .getData();
            }
            return null;
        }
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onMapClick(GeoPoint arg0) {
        pop.hidePop();
    }

    @Override
    public void onMapDoubleClick(GeoPoint arg0) {
    }

    @Override
    public void onMapLongClick(GeoPoint arg0) {
    }
}
