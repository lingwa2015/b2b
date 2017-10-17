package com.sxc.natasha.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sxc.natasha.common.CommonUtil;
import com.sxc.natasha.common.DateUtil;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.OrderStatusEnum;
import com.sxc.natasha.domain.Order;
import com.sxc.natasha.domain.OrderSKU;
import com.sxc.natasha.ui.HelpCommonActivity;
import com.sxc.natasha.ui.OrderDetailActivity;
import com.sxc.natasha.ui.PayOrderActivity;
import com.sxc.natasha.ui.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo on 15/3/12.
 */
public class OrderListAdapter extends BaseAdapter {


    private List<Order> dataList;
    private int resouceId;
    private Activity activity;
    private LayoutInflater mInflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");


    public void setDataList(List<Order> dataList) {
        this.dataList = dataList;
    }

    public OrderListAdapter(List<Order> dataList,int resouceId,Activity activity){
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


    private void processOrderButton(Button orderBtn,final Order order){
        Resources resource = (Resources) activity.getResources();

        if(order.getOrderStatus() == OrderStatusEnum.UNPAY.getId()){
            //orderNoTv.setText();
            orderBtn.setText("");
            Drawable payBtn = resource.getDrawable(R.drawable.order_list_pay_btn_selector);
            if(payBtn != null){
                orderBtn.setBackground(payBtn);
            }

            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, PayOrderActivity.class);
                    intent.putExtra("page", "立即支付");
                    intent.putExtra("userId", order.getBuyerId());
                    intent.putExtra("orderNo", order.getOrderNo());
                    if(order.getDiscountedFee() != null && order.getDiscountedFee() != 0l){
                        intent.putExtra("money", order.getDiscountedFee() + "");
                    }else{
                        intent.putExtra("money", order.getTotalFee() + "");
                    }

                    activity.startActivity(intent);
                }
            });
        }else if(order.getOrderStatus() == OrderStatusEnum.UNDELIVER.getId()){
            orderBtn.setText("");
            Drawable pickBtn = resource.getDrawable(R.drawable.order_list_pick_btn_selector);
            if(pickBtn != null){
                orderBtn.setBackground(pickBtn);
            }

            orderBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,OrderDetailActivity.class);
                    intent.putExtra("orderNo",order.getOrderNo());
                    activity.startActivity(intent);
                }
            });

        }else if(order.getOrderStatus() == OrderStatusEnum.WAITPICK.getId()){
            orderBtn.setText("");
            Drawable pickBtn = resource.getDrawable(R.drawable.order_list_processing_btn_selector);
            if(pickBtn != null){
                orderBtn.setBackground(pickBtn);
            }

            orderBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,OrderDetailActivity.class);
                    intent.putExtra("orderNo",order.getOrderNo());
                    activity.startActivity(intent);
                }
            });

        }else if(order.getOrderStatus() == OrderStatusEnum.CLOSE.getId()){
            orderBtn.setText("");
            Drawable pickBtn = resource.getDrawable(R.drawable.order_list_close_btn_selector);
            if(pickBtn != null){
                orderBtn.setBackground(pickBtn);
            }

            orderBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,OrderDetailActivity.class);
                    intent.putExtra("orderNo",order.getOrderNo());
                    activity.startActivity(intent);
                }
            });
        }
        else{
            orderBtn.setText("");
            Drawable pickBtn = resource.getDrawable(R.drawable.order_list_processing_btn_selector);
            if(pickBtn != null){
                orderBtn.setBackground(pickBtn);
            }

            orderBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,OrderDetailActivity.class);
                    intent.putExtra("orderNo",order.getOrderNo());
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Order order = dataList.get(position);
        Resources resource = (Resources) activity.getResources();


        convertView = this.mInflater.inflate(this.resouceId,null);

        convertView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,OrderDetailActivity.class);
                intent.putExtra("orderNo",order.getOrderNo());
                activity.startActivity(intent);

            }
        });

        TextView orderNoTv = (TextView)convertView.findViewById(R.id.item_orderNo);

        orderNoTv.setText(order.getOrderNo());

        TextView orderStateTv = (TextView)convertView.findViewById(R.id.item_orderState);



        orderStateTv.setText(OrderStatusEnum.parseId(order.getOrderStatus()).getValue());

        if(order.getOrderStatus().intValue() == OrderStatusEnum.UNPAY.getId()){
            ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.orange);
            if(csl != null){
                orderStateTv.setTextColor(csl);
            }

        }

        TextView orderPayTimeTv = (TextView)convertView.findViewById(R.id.item_payTime);
        TextView orderDeliveryTimeTv = (TextView)convertView.findViewById(R.id.item_deliveryTime);
        try{
            if(order.getPayTime() != null){
                orderPayTimeTv.setText(sdf.format(order.getPayTime()));
            }else{
                orderPayTimeTv.setText("");
            }

            orderDeliveryTimeTv.setText(sdfYMD.format(order.getPicktime()));
        }catch (Throwable e){
           // Log.e("py",e.toString());
        }
        TextView orderOrderAlertMsgTv = (TextView)convertView.findViewById(R.id.order_alertMsg);
        Map<String, Integer> diffMap = DateUtil.getDifferentHoursAndMinutes(new Date(), "20:00:00");
        if (diffMap.get("diffHours") <= 0 && diffMap.get("diffMinutes") <= 0) {
            orderOrderAlertMsgTv.setText("今天结算已经完毕！");
        } else {
            orderOrderAlertMsgTv.setText("离今天停止接受订货付款 还剩" + diffMap.get("diffHours") +"小时"
                    + diffMap.get("diffMinutes") + "分");
        }







        ListView skuListView = (ListView)convertView.findViewById(R.id.order_skuList);
        List<OrderSKU> skuList = order.getSkuList();

        int skuTotalNum = 0;
        for(OrderSKU sku : skuList){
            skuTotalNum = skuTotalNum + sku.getNum();
        }
        if(skuList != null){

            OrderItemSkuAdapter adapter1 = new OrderItemSkuAdapter(skuList,R.layout.order_sku_item,activity);
            skuListView.setAdapter(adapter1);

            CommonUtil.fixListViewHieght(skuListView);
        }


        Button orderButton = (Button)convertView.findViewById(R.id.order_button);

        TextView orderSkuNumTv = (TextView)convertView.findViewById(R.id.order_skuNum);
        orderSkuNumTv.setText("共"+skuTotalNum+"件商品");


        TextView orderTotalPriceTV = (TextView)convertView.findViewById(R.id.order_total_price);
        orderTotalPriceTV.setText(NumberTool.toYuanNumber(order.getTotalFee()));



       processOrderButton(orderButton,order);

        return convertView;
    }
}
