/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.bean.Secretary;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-19 下午03:08:16
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class PrivateSecretryInforListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Secretary> privateSecretryInforItemsList;

    public PrivateSecretryInforListAdapter(Context context,
            ArrayList<Secretary> privateSecretryInforItemsList) {
        this.context = context;
        this.privateSecretryInforItemsList = privateSecretryInforItemsList;
    }

    @Override
    public int getCount() {
        return privateSecretryInforItemsList == null ? 0
                : privateSecretryInforItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return privateSecretryInforItemsList == null ? null
                : privateSecretryInforItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PrivateSecretryInforViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.private_secretry_listinfor_item, null);
            holder = new PrivateSecretryInforViewHolder();
            holder.txtAddress = (TextView) convertView
                    .findViewById(R.id.txt_private_secretry_address);
            holder.txtArea = (TextView) convertView
                    .findViewById(R.id.txt_private_secretry_area);
            holder.txtIndustry = (TextView) convertView
                    .findViewById(R.id.txt_private_secretry_industry);
            holder.txtTitle = (TextView) convertView
                    .findViewById(R.id.txt_private_secretry_title);
            convertView.setTag(holder);
        } else {
            holder = (PrivateSecretryInforViewHolder) convertView.getTag();
        }

        Secretary item = privateSecretryInforItemsList.get(position);
        holder.txtAddress.setText(item.getAddress());
        holder.txtArea.setText(item.getRegionName());
        holder.txtIndustry.setText(item.getTypeName());
        holder.txtTitle.setText(item.getTitle());
        return convertView;
    }

}
