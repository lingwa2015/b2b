package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.adapter.PickhouseAdapter;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.BuyerPickhouse;
import com.sxc.natasha.domain.User;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreatePickupInfoActivity extends Activity {

    ListView addressListView;
    PickhouseAdapter adapter;
    String pickupStoreId = null;
    List<Map<String, String>> apiResults;
    Spinner spinner;
    //Map<城市, Map<地区, 数据ids>>
    Map<String, Map<String, List<String>>> citiesAreasAndIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomTitle();
        this.spinner = (Spinner) findViewById(R.id.create_picker_city_spinner);
        this.citiesAreasAndIds = new HashMap<String, Map<String, List<String>>>();
        createPickupInfo();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                //tv.setText(spinner.getSelectedItem().toString());
                final Map<String, List<String>> areasMap = citiesAreasAndIds.get(spinner.getSelectedItem().toString());
                final Set<String> areas = areasMap.keySet();
                if (areasMap == null)
                    return;

                FlowLayout flView = (FlowLayout) findViewById(R.id.create_picker_areas);
                flView.removeAllViews();
                TextView textView = new TextView(getApplicationContext());
                textView.setText("区域：");
                textView.setTextSize(18);
                textView.setTextColor(getResources().getColor(R.color.gray_dark));
                textView.setPadding(4, 16, 0, 0);
                flView.addView(textView);
                for (int j = 0; j < areas.size(); j++) {
                    final String areaValue = areas.toArray(new String[]{})[j];
                    Button btn = new Button(getApplicationContext());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    btn.setPadding(0, 10, 0, 0);
                    lp.setMargins(10, 0, 0, 0);
                    btn.setTextSize(16);
                    btn.setHeight(10);
                    btn.setText(areaValue);
                    btn.setLayoutParams(lp);
                    btn.setBackgroundColor(getResources().getColor(R.color.gray_light));
                    btn.setId(j);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final TextView findResultView = (TextView) findViewById(R.id.create_picker_find_result);
                            findResultView.setText("在" + areaValue + "，找到了" + areasMap.get(areaValue).size() + "个上门自提服务门店，请选择：");

                            if (apiResults == null) {
                                AbToastUtil.showToast(getApplicationContext(), "仓库数据获取失败!");
                                return;
                            }
                            List<String> areaIds = areasMap.get(areaValue);
                            List<Map<String, String>> filterMap = new ArrayList<Map<String, String>>();
                            for (String id : areaIds) {
                                for (Map<String, String> result : apiResults) {
                                    if (result.get("id").equalsIgnoreCase(id))
                                        filterMap.add(result);
                                }
                            }

                            addressListView = (ListView) findViewById(R.id.create_picker_address_listview);
//                            SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), filterMap, R.layout.create_pickup_address_layout,
//                                    new String[]{"name", "address"}, new int[]{R.id.create_picker_storehouse, R.id.create_picker_storehouse_address});
                            adapter = new PickhouseAdapter(getApplicationContext(),filterMap);
                            addressListView.setAdapter(adapter);
                            //添加点击事件
                            addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
                                    //获得选中项的HashMap对象
                                    Map<String, String> map = (HashMap<String, String>) addressListView.getItemAtPosition(arg2);
                                    pickupStoreId = map.get("id");
                                    /*ImageView imageView = new ImageView(getApplicationContext());
                                    imageView.setImageResource(R.drawable.checked_symbol);*/
                                    /*String name = map.get("name");
                                    String address = map.get("address");
                                    AbToastUtil.showToast(getApplicationContext(), "点击了id:" + pickupStoreId + ",name:" + name
                                            + ", address:" + address);*/
                                    adapter.check((String)view.getTag());
                                }
                            });
                        }
                    });
                    flView.addView(btn);
                }
                Log.v("Test", "id = " + id + "(" + areas + ")");
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

    }

    private void setCustomTitle() {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_create_pickup_info);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(R.string.title_create_pickup_info);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createPickupInfo() {
        AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
        httpUtil.get(SXC_API.GET_ALL_STORE_HOUSE, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                Log.d("添加收货信息-loadData", s);

                Type t = new TypeToken<List<Map<String, String>>>() {
                }.getType();
                Gson gson = new Gson();
                apiResults = gson.fromJson(s, t);

                //填充 Spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                for (Map<String, String> result : apiResults) {

                    List<String> ids = new ArrayList<String>();
                    Set<String> areas = new HashSet<String>();
                    //areaCode: "湖北省,武汉市,武昌区-420106",
                    String[] temp = result.get("areaCode").split(",");
                    String tempArea = subStrArea(temp[2]);
                    Map<String, List<String>> areaMaps = citiesAreasAndIds.get(temp[1]);
                    ids.add(result.get("id"));
                    if (areaMaps == null) {
                        areaMaps = new HashMap<String, List<String>>();
                    } else {
                        if (areaMaps.get(tempArea) != null) {
                            ids.addAll(areaMaps.get(tempArea));
                        }
                    }
                    areaMaps.put(tempArea, ids);
                    citiesAreasAndIds.put(temp[1], areaMaps);
                }
                Log.d("citiesAndAreas结果", citiesAreasAndIds.toString());

                adapter.addAll(citiesAreasAndIds.keySet());
                spinner.setAdapter(adapter);


                Button doOrderBtn = (Button) findViewById(R.id.create_pickhouse_button);
                doOrderBtn.setOnClickListener(new BtnClickListener());
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFinish() {
            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                Log.e(getClass().getName(), "加载数据失败");
            }
        });
    }

    private String subStrArea(String str) {
        //武昌区-420106
        if (StringUtils.isBlank(str)) {
            return "";
        }
        int endIndex = str.indexOf("-");
        return str.substring(0, endIndex);
    }

    public class BtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView pickerNameView = (TextView) findViewById(R.id.create_picker_name);
            String pickerName = pickerNameView.getText().toString();
            TextView pickerPhoneView = (TextView) findViewById(R.id.create_picker_phone);
            String pickerPhone = pickerPhoneView.getText().toString();

            if (StringUtils.isEmpty(pickerName)) {
                AbToastUtil.showToast(getApplicationContext(), "请填写提货人姓名");
                return;
            }

            if (StringUtils.isEmpty(pickerPhone)) {
                AbToastUtil.showToast(getApplicationContext(), "请填写提货人联系方式");
                return;
            }
            //保存
            User user = UserCache.getUser(getApplicationContext());
            //SXC_API.GET_CTREATE_PICKUP_INFO: pickerName={0}&pickerPhone={1}&storehouseId={2}&userId={3}
            String reqUrl = null;
            byte[] tempPickName = Base64.encode(pickerName.getBytes(), Base64.NO_WRAP);
            reqUrl = MessageFormat.format(SXC_API.GET_CTREATE_PICKUP_INFO, new String(tempPickName), pickerPhone, pickupStoreId, user.getId());
            AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
            httpUtil.get(reqUrl, new AbStringHttpResponseListener() {
                @Override
                public void onSuccess(int i, String s) {
                    Type t = new TypeToken<Map<String, String>>() {
                    }.getType();
                    Gson gson = new Gson();
                    Map<String, String> resultMap = gson.fromJson(s, t);
                    if (resultMap != null && !StringUtils.isBlank(resultMap.get("result"))) {
                        AbToastUtil.showToast(getApplicationContext(), "添加提货信息成功");
                        finish();
                    } else {
                        AbToastUtil.showToast(getApplicationContext(), "添加提货信息失败");
                    }
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {

                }

                @Override
                public void onFailure(int i, String s, Throwable throwable) {
                    Log.e(CreatePickupInfoActivity.class.getName(), s, throwable);
                    AbToastUtil.showToast(getApplicationContext(), "添加提货信息失败");
                }
            });
        }
    }
}