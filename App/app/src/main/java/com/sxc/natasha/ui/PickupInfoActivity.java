package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.adapter.PickupInfoAdapter;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.CollectionUtil;
import com.sxc.natasha.common.ObjectUtils;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.BuyerPickhouse;
import com.sxc.natasha.domain.User;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickupInfoActivity extends Activity {

    private Map<String, List<Map<String, String>>> resultMap = new HashMap<String, List<Map<String, String>>>();
    private ListView pickupInfoListView;
    private PickupInfoAdapter pickupInfoAdapter;
    private List<Map<String, String>> pickupInfos =null;
    private String fromPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomTitle();
        fromPage = getIntent().getStringExtra("fromPage");
        Log.d(getClass().getName(), "fromPage:" + fromPage);

        ImageButton btn = (ImageButton) findViewById(R.id.pickup_info_add_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCreatePickupInfo();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = UserCache.getUser(getApplicationContext());
        loadPickupInfo(user.getMobilePhone());
    }

    private void toCreatePickupInfo() {
        Intent intent = new Intent(PickupInfoActivity.this, CreatePickupInfoActivity.class);
        startActivity(intent);
    }

    private void setCustomTitle() {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_pickup_info);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(R.string.title_activity_pickup_info);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadPickupInfo(final String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            Log.w(this.getClass().getName(), "提货人手机号码为必需参数");
            AbToastUtil.showToast(getApplicationContext(), "用户信息异常，请重新登录！");
            return;
        }
        Log.d(getClass().getName(), "用户手机号码：" + phoneNumber);
        AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
        httpUtil.get(SXC_API.GET_STORE_HOUSE_BY_PHONE + phoneNumber, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(final int i, String s) {
                Log.d("in PickupInfoActivity", s);

                Type t = new TypeToken<Map<String, List<Map<String, String>>>>() {
                }.getType();
                Gson gson = new Gson();

                resultMap = gson.fromJson(s, t);

                pickupInfos = resultMap.get(phoneNumber);
                if (CollectionUtil.isEmpty(pickupInfos)) return;
                //填充listView
                pickupInfoListView = (ListView) findViewById(R.id.pickup_info_listView);
                pickupInfoAdapter = new PickupInfoAdapter(getApplicationContext(), pickupInfos);
                pickupInfoListView.setAdapter(pickupInfoAdapter);
                pickupInfoListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                pickupInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!StringUtils.isBlank(fromPage) && fromPage.equalsIgnoreCase("userCenter")) {
                            Map<String,String> pickupInfo = pickupInfos.get(position);
                            BuyerPickhouse buyerPickhouse = new BuyerPickhouse();
                            buyerPickhouse.setId(Integer.parseInt(pickupInfo.get("id")));
                            buyerPickhouse.setName(pickupInfo.get("pickupName"));
                            buyerPickhouse.setPhone(pickupInfo.get("pickupPhone"));
                            buyerPickhouse.setStorehouseId(Integer.parseInt(pickupInfo.get("storeHouseId")));
                            User user = UserCache.getUser(getApplicationContext());
                            buyerPickhouse.setUserId(user.getId());
                            Intent intent = new Intent(PickupInfoActivity.this,EditPickupInfoActivity.class);
                            intent.putExtra("buyerPickhouse", buyerPickhouse);
                            startActivity(intent);
                        } else {

                           /* Log.v(getClass().getName(), "你点击了ListView条目" + position + ", 默认仓库选中了：" + pickupInfos.get(position).get("storeHouseId"));//在LogCat中输出信息

                            for (int i = 0; i < parent.getCount(); i++) {
                                View v = parent.getChildAt(i);
                                if (position == i) {
                                    v.setBackgroundColor(Color.RED);
                                } else {
                                    v.setBackgroundColor(Color.TRANSPARENT);
                                }
                            }*/

                            for (Map<String, String> pickUpInfoMap : pickupInfoAdapter.getPickupInfos()) {
                                if (pickUpInfoMap.get("id").equalsIgnoreCase(pickupInfos.get(position).get("id"))) {
                                    pickUpInfoMap.put("defaultPickHouse", "1");
                                } else {
                                    pickUpInfoMap.put("defaultPickHouse", "0");
                                }
                            }

                            //选中跳转到 下单页面
                            String storeHouseId = pickupInfos.get(position).get("id");
                            /*ImageView imageView = (ImageView) findViewById(R.id.pickup_info_checked);
                            imageView.setImageResource(R.drawable.checked_symbol);*/
                            Intent intent = new Intent(getApplication(), DoOrderActivity.class);
                            intent.putExtra("storeHouseId", storeHouseId);
                            startActivity(intent);

                        }
                    }
                });
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {

            }
        });
    }
}
