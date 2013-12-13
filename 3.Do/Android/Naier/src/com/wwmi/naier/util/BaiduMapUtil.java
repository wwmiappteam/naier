/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.util;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.wwmi.naier.R;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-7-31 下午05:38:10
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class BaiduMapUtil {

    /**
     * Description:加载百度地图，设置缩放大小，设置控制工具
     * 
     * @param mMapView
     */
    public static void loadBaiduMap(MapView mMapView, Activity activity) {
        // Toast.makeText(activity, "正在加载地图...", Toast.LENGTH_LONG).show();

        // 设置启用内置的缩放控件
        MapController mMapController = mMapView.getController();
        // 得到控制权，可以用它控制平移和缩放
        GeoPoint point = new GeoPoint((int) (30.54775 * 1E6),
                (int) (104.07705 * 1E6));
        mMapController.setCenter(point);
        mMapController.setZoom(19);
        // 创建个overlay
        Overlay overlay = new Overlay(activity.getResources().getDrawable(
                R.drawable.icon_gcoding), mMapView);
        OverlayItem item1 = new OverlayItem(point, " ", "");
        item1.setMarker(activity.getResources().getDrawable(
                R.drawable.icon_gcoding));
        overlay.addItem(item1);

        mMapView.getOverlays().add(overlay);

        // 刷新地图

        mMapView.refresh();
    }

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
            // TODO Auto-generated constructor stub
        }
    }
}
