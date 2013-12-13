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
import com.wwmi.naier.bean.JsonService;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-21 下午05:56:58
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class ServiceIntroduceInforListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JsonService.Service> list;

    public ServiceIntroduceInforListAdapter(Context context,
            ArrayList<JsonService.Service> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
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
                    R.layout.service_introduce_listinfor_item, null);
            holder = new Holder();
            holder.txtIntroduceItem = (TextView) convertView
                    .findViewById(R.id.txt_service_introduce_itemcontent);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.txtIntroduceItem.setText(list.get(position).getServTitle());
        return convertView;
    }

    class Holder {
        public TextView txtIntroduceItem;
    }
}
