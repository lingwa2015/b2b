package com.sxc.natasha.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxc.natasha.ui.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Ming.Zi on 3/20/2015.
 */
public class PickupInfoAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private List<Map<String, String>> pickupInfos = null;
    private Context context = null;
    private int  selectItem = -1;

    public PickupInfoAdapter(Context context, List<Map<String, String>> pickupInfos) {
        //根据context上下文加载布局
        this.mInflater = LayoutInflater.from(context);
        this.pickupInfos = pickupInfos;
        this.context = context;
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public int getCount() {
        return pickupInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return pickupInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PickupInfoViewHolder pickupInfoViewHolder = null;
        convertView = mInflater.inflate(R.layout.pickup_info_layout, null);
        //如果缓存convertView为空，则需要创建View
        /*if(convertView == null) {
            pickupInfoViewHolder = new PickupInfoViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.pickup_info_layout, null);
            pickupInfoViewHolder.img = (ImageView)convertView.findViewById(R.id.pickup_info_image);
            pickupInfoViewHolder.pickerName = (TextView)convertView.findViewById(R.id.pickup_info_picker_name);
            pickupInfoViewHolder.pickerPhone = (TextView)convertView.findViewById(R.id.pickup_info_picker_phone);
            pickupInfoViewHolder.pickHouseName = (TextView)convertView.findViewById(R.id.pickup_info_storehouse_name);
            pickupInfoViewHolder.pickHouseAddress = (TextView)convertView.findViewById(R.id.pickup_info_storehouse_addr);
            pickupInfoViewHolder.checkedImg = (ImageView)convertView.findViewById(R.id.pickup_info_checked);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(pickupInfoViewHolder);
        } else {
            pickupInfoViewHolder = (PickupInfoViewHolder)convertView.getTag();
        }
        pickupInfoViewHolder.img.setImageResource(R.drawable.transportation);
        pickupInfoViewHolder.pickerName.setText(pickupInfos.get(position).get("pickupName"));
        pickupInfoViewHolder.pickerPhone.setText(pickupInfos.get(position).get("pickupPhone"));
        pickupInfoViewHolder.pickHouseName.setText(pickupInfos.get(position).get("storeHouseName"));
        pickupInfoViewHolder.pickHouseAddress.setText(pickupInfos.get(position).get("storeHouseAddress"));*/

        ImageView transferImageView = (ImageView)convertView.findViewById(R.id.pickup_info_image);
        transferImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.transportation));

        TextView pickerNameView = (TextView)convertView.findViewById(R.id.pickup_info_picker_name);
        pickerNameView.setText(pickupInfos.get(position).get("pickupName"));

        TextView pickerPhoneView = (TextView)convertView.findViewById(R.id.pickup_info_picker_phone);
        pickerPhoneView.setText(pickupInfos.get(position).get("pickupPhone"));

        TextView pickHouseNameView = (TextView)convertView.findViewById(R.id.pickup_info_storehouse_name);
        pickHouseNameView.setText(pickupInfos.get(position).get("storeHouseName"));

        TextView pickHouseAddressView = (TextView)convertView.findViewById(R.id.pickup_info_storehouse_addr);
        pickHouseAddressView.setText(pickupInfos.get(position).get("storeHouseAddress"));
        /*if (pickupInfos.get(position).get("defaultPickHouse").equalsIgnoreCase("1")) {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.pickup_info_checked);
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.address_07));
        }*/

        return convertView;
    }

    public List<Map<String, String>> getPickupInfos() {
        return pickupInfos;
    }

    class PickupInfoViewHolder
    {
        public ImageView img;
        public TextView pickerName;
        public TextView pickerPhone;
        public TextView pickHouseName;
        public TextView pickHouseAddress;
        //public ImageView checkedImg;
    }
}
