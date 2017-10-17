package com.sxc.natasha.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sxc.natasha.domain.OrderSKU;
import com.sxc.natasha.ui.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bobo on 15/3/13.
 */
public class OrderItemSkuAdapter extends BaseAdapter {

    private List<OrderSKU> dataList;
    private int resouceId;
    private Activity activity;
    private LayoutInflater mInflater;

    public OrderItemSkuAdapter(List<OrderSKU> dataList,int resouceId,Activity activity){
        this.dataList = dataList;
        this.resouceId = resouceId;
        this.activity = activity;
        this.mInflater = LayoutInflater.from(this.activity);


    }

    @Override
    public int getCount() {
        if(dataList == null) return 0;

        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderSKU sku = dataList.get(position);
        convertView = mInflater.inflate(resouceId,null);
        TextView skuNameTv = (TextView)convertView.findViewById(R.id.order_item_sku_name);
        skuNameTv.setText(sku.getItemName());

        TextView skuNumTv = (TextView)convertView.findViewById(R.id.order_item_sku_num);
        skuNumTv.setText(sku.getNum() + "");

        return convertView;
    }
}
