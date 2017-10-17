package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.DateUtil;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.domain.Order;
import com.sxc.natasha.domain.OrderSKU;
import com.sxc.natasha.domain.PickStoreHouse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by longpo on 2015/3/21.
 */
public class PaySuccessActivity extends Activity {
    private LayoutInflater mInflater = null;
    private final AbHttpUtil httpUtil = AbHttpUtil.getInstance(this);
    private PaySuccessActivity myselfActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.pay_success);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
        this.mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView title = (TextView) findViewById(R.id.title);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);
        title.setText("下单");
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String orderNo = getIntent().getStringExtra("orderNo");

        httpUtil.get(SXC_API.GET_ORDER_DETAIL + orderNo,new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    Type t = new TypeToken<Order>(){}.getType();
                    Gson gson = new Gson();
                    Order order = gson.fromJson(s, Order.class);
                    Log.d("PaySuccessActivity", order.toString());
                    // 订单号
                    ((TextView)findViewById(R.id.order_no)).setText(orderNo);
                    //提货时间
                    ((TextView)findViewById(R.id.pick_time)).setText(DateUtil.getTimeByFromat(order.getPayTime(), "yyyy-MM-dd") +" 05:00-20:00");
                    //商品信息
                    LinearLayout list = (LinearLayout)findViewById(R.id.show_product_item_list);
                    list.removeAllViewsInLayout();
                    for (OrderSKU sku : order.getSkuList()) {
                        TextView show = new TextView(myselfActivity());
                        show.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                        show.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                        show.setText(sku.getItemName() +" "+ sku.getNum() +"件");
                        show.setTextColor(getResources().getColor(android.R.color.darker_gray));
                        list.addView(show);
                    }
                    //提货地址
                    setPickHouseAddress(order.getPickhouseId());
                    //提货人
                    //提货电话
                    setPickNameAndPhone(order.getBuyerPickhouseId());
                    //商品总价
                    ((TextView)findViewById(R.id.product_all_money)).setText(NumberTool.toYuanNumber(order.getTotalFee()));

                    Long discountVal = 0l;
                    if(order.getDiscountedFee() != null && order.getDiscountedFee() != 0l){
                        discountVal = order.getTotalFee() - order.getDiscountedFee();
                        ((TextView)findViewById(R.id.pay_success_discount)).setText(NumberTool.toYuanNumber(discountVal));
                        // 订单总价
                        ((TextView)findViewById(R.id.total_order_money)).setText(NumberTool.toYuanNumber(order.getDiscountedFee()));
                    }else{
                        //优惠
                        ((TextView)findViewById(R.id.pay_success_discount)).setText("0.0");
                        // 订单总价
                        ((TextView)findViewById(R.id.total_order_money)).setText(NumberTool.toYuanNumber(order.getTotalFee()));
                    }


                } catch (Throwable e){
                    Log.e("error",e.getMessage());
                    AbToastUtil.showToast(getApplicationContext(), e.getMessage());
                }
            }
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                Log.d("myorderFragment","onFinish");

            }

            @Override
            public void onFailure(int i, String s, Throwable error) {
                AbToastUtil.showToast(getApplicationContext(), error.getMessage());

            }
        });

        // 查看订单
        ((Button)findViewById(R.id.check_order_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myselfActivity(),OrderDetailActivity.class);
                intent.putExtra("orderNo", orderNo);
                startActivity(intent);
            }
        });
        // 返回主页
        ((Button)findViewById(R.id.back_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myselfActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setPickHouseAddress(int pickHouseId) {

        httpUtil.get(SXC_API.GET_STORE_HOUSE + pickHouseId,new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    Gson gson = new Gson();
                    PickStoreHouse house = gson.fromJson(s, PickStoreHouse.class);
                    //提货地址
                    ((TextView) findViewById(R.id.pick_house_address)).setText(house.getAddress());
                } catch (Throwable e){
                    Log.e("error",e.getMessage());
                    AbToastUtil.showToast(getApplicationContext(), e.getMessage());
                }
            }

            @Override
            public void onStart() {
            }
            @Override
            public void onFinish() {
                Log.d("setPickHouseAddress","onFinish");
            }
            @Override
            public void onFailure(int i, String s, Throwable error) {
                AbToastUtil.showToast(getApplicationContext(), error.getMessage());
            }
        });
    }

    private void setPickNameAndPhone(int buyerPickhouseId) {
        httpUtil.get(SXC_API.GET_BUYER_PICK_HOUSE + buyerPickhouseId,new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    JSONObject obj = new JSONObject(s);
                    //提货姓名
                    ((TextView)findViewById(R.id.pick_name)).setText(obj.getString("name"));
                    //提货人电话
                    ((TextView)findViewById(R.id.pick_phone)).setText(obj.getString("phone"));
                } catch (Throwable e){
                    Log.e("error",e.getMessage());
                    AbToastUtil.showToast(getApplicationContext(), e.getMessage());
                }
            }

            @Override
            public void onStart() {
            }
            @Override
            public void onFinish() {
                Log.d("setPickHouseAddress","onFinish");
            }
            @Override
            public void onFailure(int i, String s, Throwable error) {
                AbToastUtil.showToast(getApplicationContext(), error.getMessage());
            }
        });
    }
}
