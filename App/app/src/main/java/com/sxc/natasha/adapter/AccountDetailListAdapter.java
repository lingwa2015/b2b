package com.sxc.natasha.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.domain.AccountDetail;
import com.sxc.natasha.domain.AccountModeEnum;
import com.sxc.natasha.domain.Order;
import com.sxc.natasha.ui.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by longpo on 2015/3/23.
 */
public class AccountDetailListAdapter extends BaseAdapter {

    private List<AccountDetail> dataList;
    private int resouceId;
    private Activity activity;
    private LayoutInflater mInflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");


    public AccountDetailListAdapter(List<AccountDetail> dataList, int resouceId, Activity activity){
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AccountDetail detail = dataList.get(position);
        convertView = this.mInflater.inflate(this.resouceId, null);
        AccountModeEnum accountEnum = AccountModeEnum.parseId(detail.getChangeType());
        ((TextView)convertView.findViewById(R.id.bill_name)).setText(accountEnum.getValue());
        ((TextView)convertView.findViewById(R.id.bill_date)).setText(sdfYMD.format(detail.getChangeTime()));
        ((TextView)convertView.findViewById(R.id.balance_snapshoot)).setText(NumberTool.toYuanNumber(detail.getBalanceSnapshoot()));
        TextView changeView = (TextView)convertView.findViewById(R.id.bill_change_money);
        if (accountEnum == AccountModeEnum.CASH
                || accountEnum == AccountModeEnum.POS
                || accountEnum == AccountModeEnum.REFUND) {
            changeView.setText("+" + NumberTool.toYuanNumber(detail.getChangeFee()));
            changeView.setTextColor(Color.GREEN);
        } else if (accountEnum == AccountModeEnum.EXPEND) {
            changeView.setText("-" +NumberTool.toYuanNumber(detail.getChangeFee()));
            changeView.setTextColor(Color.RED);
        }
        return convertView;
    }
}
