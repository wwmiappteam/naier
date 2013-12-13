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
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.bean.SecretaryTpyeFather;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-20 下午04:41:40
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class PrivateSecretryIndustryDialogAdapter extends
        BaseExpandableListAdapter {
    private Context context;
    private List<SecretaryTpyeFather> group;

    public PrivateSecretryIndustryDialogAdapter(Context context,
            List<SecretaryTpyeFather> group) {
        this.context = context;
        this.group = group;
    }

    // -----------------------Child------------------------------
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return group.get(groupPosition).getChildTypeList();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        String string = group.get(groupPosition).getChildTypeList().get(childPosition).getTypeName();
        Holder holder = null;
        return getView(holder, convertView, string);
    }

    class Holder {
        public TextView txtContent;
    }

    /**
     * Description:获取view
     * 
     * @param holder
     * @param convertView
     * @param string
     * @return
     */
    public View getView(Holder holder, View convertView, String string) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    R.layout.private_secretary_industry_dialog_item, null);
            holder = new Holder();
            holder.txtContent = (TextView) convertView
                    .findViewById(R.id.txt_private_secretary_dialog_industry);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.txtContent.setText(string);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return group.get(groupPosition).getChildTypeList().size();
    }

    // ------------------------Group-----------------------
    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String string = group.get(groupPosition).getTypeName();
        Holder holder = null;
        return getView(holder, convertView, string);
    }

    // ------------------------------------------------------------
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
