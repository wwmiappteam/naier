/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.bean.SecretaryRegion;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-20 下午02:37:34
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class PrivateSecretryAreaDialogListAdapter extends BaseAdapter {
    private Context context;
    private List<SecretaryRegion> areaArrayList;

    public PrivateSecretryAreaDialogListAdapter(Context context,
            List<SecretaryRegion> areaArrayList) {
        this.context = context;
        this.areaArrayList = areaArrayList;
    }

    @Override
    public int getCount() {
        return areaArrayList == null ? 0 : areaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return areaArrayList == null ? null : areaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.private_secretary_area_dialog_item, null);
            holder = new Holder();
            holder.txtAreaName = (TextView) convertView
                    .findViewById(R.id.txt_private_secretary_dialog_area);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.txtAreaName.setText(areaArrayList.get(position).getRegionName());
        return convertView;
    }

    class Holder {
        public TextView txtAreaName;
    }

}
