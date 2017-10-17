package com.sxc.natasha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sxc.natasha.ui.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longpo on 15/3/28.
 */
public class PickhouseAdapter extends BaseAdapter{
    private Map<String,RadioButton> radioGroup;
    private Context context;
    private List<Map<String,String>> data;
    public PickhouseAdapter(Context context,List<Map<String,String>> data){
        radioGroup = new HashMap<String, RadioButton>();
        this.context = context;
        this.data = data;
    }

    @Override
    public void notifyDataSetChanged() {
        radioGroup.clear();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.create_pickup_address_layout,null);
        Map<String,String> record = (Map<String, String>) getItem(position);
        TextView pickerStorehouse = (TextView) convertView.findViewById(R.id.create_picker_storehouse);
        TextView pickerStorehouseAddress = (TextView) convertView.findViewById(R.id.create_picker_storehouse_address);
        RadioButton rb = (RadioButton) convertView.findViewById(R.id.pickup_radio);
        String tag = String.valueOf(position);
        radioGroup.put(tag,rb);
        pickerStorehouse.setText(record.get("name"));
        pickerStorehouseAddress.setText(record.get("address"));
        convertView.setTag(tag);
        return convertView;
    }

    public void check(String tag){
        for(String key : radioGroup.keySet()){
            RadioButton rb = radioGroup.get(key);
            if(key.equals(tag)){
                rb.setChecked(true);
            }else{
                rb.setChecked(false);
            }
        }
    }
}
