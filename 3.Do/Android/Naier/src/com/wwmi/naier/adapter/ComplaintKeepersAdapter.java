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

public class ComplaintKeepersAdapter extends BaseAdapter {
    private Context context;
    private List<Order> keeperList;
    private BitmapLoader loader;

    public ComplaintKeepersAdapter(Context context, List<Order> keeperList) {
        this.context = context;
        this.keeperList = keeperList;
        loader = new BitmapLoader();
    }

    @Override
    public int getCount() {
        return keeperList == null ? 0 : keeperList.size();
    }

    @Override
    public Object getItem(int position) {
        return keeperList == null ? null : keeperList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder {
        public ImageView imgPhoto;
        public TextView txtKeeper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater
                    .inflate(R.layout.complaint_dialog_item, null);
            holder = new Holder();
            holder.imgPhoto = (ImageView) convertView
                    .findViewById(R.id.complaint_dialog_photo);
            holder.txtKeeper = (TextView) convertView
                    .findViewById(R.id.complaint_dialog_name);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        
        Order item = keeperList.get(position);
        holder.imgPhoto.setImageResource(R.drawable.personal_photo_default);
        Bitmap bitmapInCache = loader.loadBitmap(position, holder.imgPhoto,
                item.getKeeperPhoto(), new BitmapLoader.ImageCallBack() {
                    @Override
                    public void imageLoad(ImageView imageView, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        ComplaintKeepersAdapter.this.notifyDataSetChanged();
                    }
                });
        if (bitmapInCache != null) {
            holder.imgPhoto.setImageBitmap(bitmapInCache);
        }
        holder.txtKeeper.setText(keeperList.get(position).getKeeperName() + ","
                + keeperList.get(position).getKeeperTypeDescription());
        return convertView;
    }

}
