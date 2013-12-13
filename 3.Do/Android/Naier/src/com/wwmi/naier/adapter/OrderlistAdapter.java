package com.wwmi.naier.adapter;

import java.util.List;

import com.wwmi.naier.R;
import com.wwmi.naier.bean.Custom.Order;
import com.wwmi.naier.util.BitmapLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderlistAdapter extends BaseAdapter {

    private Context context;
    private List<Order> list;
    private BitmapLoader loader;

    public OrderlistAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
        loader = new BitmapLoader();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderlistHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.personal_orderlist_item,
                    null);
            holder = new OrderlistHolder();
            holder.photo = (ImageView) convertView
                    .findViewById(R.id.orderlist_item_photo);
            holder.name = (TextView) convertView
                    .findViewById(R.id.orderlist_item_name);
            holder.time = (TextView) convertView
                    .findViewById(R.id.orderlist_item_time);
            holder.type = (TextView) convertView
                    .findViewById(R.id.orderlist_item_type);
            convertView.setTag(holder);
        } else {
            holder = (OrderlistHolder) convertView.getTag();
        }

        Order item = list.get(position);
        holder.photo.setImageResource(R.drawable.personal_photo_default);
        Bitmap bitmapInCache = loader.loadBitmap(position, holder.photo,
                item.getKeeperPhoto(), new BitmapLoader.ImageCallBack() {

                    @Override
                    public void imageLoad(ImageView imageView, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        OrderlistAdapter.this.notifyDataSetChanged();
                    }
                });
        if (bitmapInCache != null) {
            holder.photo.setImageBitmap(bitmapInCache);
        }
        holder.name.setText(item.getKeeperName());
        holder.time.setText(item.getStartTime() + "\n" + item.getEndTime());
        holder.type.setText(item.getKeeperTypeDescription());

        return convertView;
    }

}
