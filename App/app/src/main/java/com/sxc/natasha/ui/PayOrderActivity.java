package com.sxc.natasha.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.adapter.OrderListAdapter;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.CommonUtil;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.domain.Order;
import com.sxc.natasha.domain.PickStoreHouse;
import com.sxc.natasha.domain.User;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by longpo 15/3/14
 */
public class PayOrderActivity extends Activity {
    private DecimalFormat df = new DecimalFormat("######0.00");
    private LayoutInflater mInflater = null;
    private final AbHttpUtil httpUtil = AbHttpUtil.getInstance(this);

    private PayOrderActivity myself() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.pay_order_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
        this.mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView title = (TextView) findViewById(R.id.title);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);
        String page = getIntent().getStringExtra("page");
        title.setText(page);
        Integer userId = getIntent().getIntExtra("userId", -1);
        final String orderNo = getIntent().getStringExtra("orderNo");
        String moneyStr = getIntent().getStringExtra("money");
        final Button payBtn = (Button)findViewById(R.id.pay_btn);
        payBtn.setVisibility(View.INVISIBLE);
        if (moneyStr == null || moneyStr.trim().length() < 0) {
            AbToastUtil.showToast(getApplicationContext(), "无效的订单金额");
            return;
        }
        final Long money = Long.parseLong(moneyStr);
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取用户余额
        httpUtil.get(SXC_API.GET_USER_ACCOUNT +"?userId=" + userId,new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    Log.d("PayOrderActivity",s);
                    JSONObject obj = new JSONObject(s);
                    Long balanceFen = obj.getLong("balance");
                    ((TextView) findViewById(R.id.order_money)).setText("￥" + NumberTool.toYuanNumber(money));
                    ((TextView) findViewById(R.id.customer_balance)).setText("￥" + NumberTool.toYuanNumber(balanceFen));

                    if((balanceFen - money) < 0.0d ){
                        ((TextView)findViewById(R.id.customer_pay_more)).setText("￥" +NumberTool.toYuanNumber(money - balanceFen));
                    }else{
                        ((TextView)findViewById(R.id.customer_pay_more)).setText("￥0.0");
                    }
                    payBtn.setVisibility(View.VISIBLE);
                    payBtn.setTag(money - balanceFen > 0 ? true : false); // 能否支付，是否需要充值
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
                Log.d("PayOrderActivity","onFinish");

            }

            @Override
            public void onFailure(int i, String s, Throwable error) {
                AbToastUtil.showToast(getApplicationContext(), error.getMessage());

            }
        });

        //支付订单
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean recharge = Boolean.parseBoolean(payBtn.getTag().toString());
                if (recharge) {
                    AbToastUtil.showToast(getApplicationContext(), "对不起，您的帐户余额不足!");
                    return;
                }
                httpUtil.get(SXC_API.PAY_ORDER + "?orderNo=" + orderNo, new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int i, String s) {
                        try {
                            if (s.toLowerCase().startsWith("fail:")) {
                                s = s.substring(5);
                                AbToastUtil.showToast(getApplicationContext(), s);
                            } else if (s.toLowerCase().equals("success")) {
                                showPaySuccess(orderNo);
                            }
                        } catch (Throwable e) {
                            Log.e("error", e.getMessage());
                            AbToastUtil.showToast(getApplicationContext(), e.getMessage());
                        }
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFinish() {
                        Log.d("PayOrderActivity", "onFinish");
                    }

                    @Override
                    public void onFailure(int i, String s, Throwable error) {
                        AbToastUtil.showToast(getApplicationContext(), error.getMessage());

                    }
                });
            }
        });

        //显示充值
        RelativeLayout rechargeView = (RelativeLayout)findViewById(R.id.pay_order_recharge);
        rechargeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = mInflater.inflate(R.layout.pay_order_recharge_layout, null);
                loadRechargeInfo(view);
            }
        });
        //显示我的订单
        ((TextView)findViewById(R.id.my_order_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyOrderActivity();
            }
        });
    }

    private void showMyOrderActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("pageId", R.id.id_tab_myorder);
        startActivity(intent);
    }


    private void showRecharge(final View view) {
        final AlertDialog dialog = new AlertDialog.Builder(myself()).create();
        dialog.show();
        dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));//(R.layout.dialog_normal_layout);
        ((Button)view.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((ImageButton)view.findViewById(R.id.custom_manager_call_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + v.getTag().toString()));
                myself().startActivity(intent);
            }
        });

        ((ImageButton)view.findViewById(R.id.market_call_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + v.getTag().toString()));
                myself().startActivity(intent);
            }
        });
    }

    private void showPaySuccess(String orderNo) {
        Intent intent = new Intent(this, PaySuccessActivity.class);
        intent.putExtra("orderNo", orderNo);
        startActivity(intent);
        finish();
    }


    private void loadRechargeInfo(final View view) {
        User user = UserCache.getUser(getApplicationContext());
        httpUtil.get(SXC_API.GET_STORE_HOUSE + user.getPickhoseId(),new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    Gson gson = new Gson();
                    PickStoreHouse house = gson.fromJson(s, PickStoreHouse.class);
                    //客户经理名称
                    ((TextView)view.findViewById(R.id.custom_manager_name)).setText(house.getManager());
                    //客户经理电话
                    ((TextView)view.findViewById(R.id.custom_manager_phone)).setText(house.getPhone());
                    //
                    ((ImageButton)view.findViewById(R.id.custom_manager_call_btn)).setTag(house.getPhone());
                    // 农贸市场的服务站名称
                    ((TextView)view.findViewById(R.id.market_name)).setText(house.getManager());
                    //服务站电话
                    ((TextView)view.findViewById(R.id.market_phone)).setText(house.getPhone());
                    //
                    ((ImageButton)view.findViewById(R.id.market_call_btn)).setTag(house.getPhone());
                    //服务站地址
                    ((TextView)view.findViewById(R.id.market_address)).setText(house.getAddress());
                    showRecharge(view);
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
