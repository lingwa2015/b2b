package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.internal.Pair;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.adapter.PickhouseAdapter;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.BuyerPickhouse;
import com.sxc.natasha.domain.User;
import com.sxc.natasha.ui.R;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EditPickupInfoActivity extends Activity {

    private ListView lv_pickerAddress = null;
    private List<Map<String, String>> addressParam = null;
    private List<PickhouseRecord> pickhouseList = null;
    private PickhouseAdapter addressAdapter = null;
    private TextView et_pickerName = null;
    private TextView et_pickerPhone = null;
    private Button saveButton = null;

    private BuyerPickhouse buyerPickhouse = null;
    private Integer selectPickhouseId = null;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setCustomTitle();
            initView();
            buyerPickhouse = (BuyerPickhouse) getIntent().getSerializableExtra("buyerPickhouse");
            et_pickerName.setText(buyerPickhouse.getName());
            et_pickerPhone.setText(buyerPickhouse.getPhone());
            user = UserCache.getUser(getApplicationContext());
            initEvent();
            Log.d(EditPickupInfoActivity.class.getName(), "loadpickupinfo");
            loadPickupInfo();
        } catch (Throwable t) {
            Log.e(EditPickupInfoActivity.class.getName(), "", t);
        }
    }

    private void initView() {
        lv_pickerAddress = (ListView) findViewById(R.id.picker_address_listview);
        et_pickerName = (TextView) findViewById(R.id.picker_name);
        et_pickerPhone = (TextView) findViewById(R.id.picker_phone);
        saveButton = (Button) findViewById(R.id.save_button);
    }

    private void loadPickupInfo() {
        AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
        httpUtil.get(SXC_API.GET_ALL_STORE_HOUSE, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {

                    Log.d(EditPickupInfoActivity.class.getName(), s);
                    pickhouseList = new Gson().fromJson(s, new TypeToken<List<PickhouseRecord>>() {
                    }.getType());
                    addressParam = new ArrayList<Map<String, String>>();

                    for (PickhouseRecord record : pickhouseList) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("name", record.getName());
                        map.put("address", record.getAddress());
                        addressParam.add(map);
                    }

                    addressAdapter = new PickhouseAdapter(getApplicationContext(),addressParam);
                    lv_pickerAddress.setAdapter(addressAdapter);
                    lv_pickerAddress.setClickable(true);


                } catch (Exception e) {
                    Log.e(EditPickupInfoActivity.class.getName(), "", e);
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
                AbToastUtil.showToast(EditPickupInfoActivity.this, "加载提货仓数据失败");
                Log.e(getClass().getName(), "加载提货仓数据失败", throwable);
            }
        });
    }


    private void initEvent() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pickerName = et_pickerName.getText().toString();
                if (StringUtils.isEmpty(pickerName)) {
                    AbToastUtil.showToast(EditPickupInfoActivity.this, "请填写提货人姓名");
                    et_pickerName.requestFocus();
                    return;
                }
                buyerPickhouse.setName(pickerName);
                String pickerPhone = et_pickerPhone.getText().toString();
                if (StringUtils.isEmpty(pickerPhone)) {
                    AbToastUtil.showToast(EditPickupInfoActivity.this, "请填写提货人电话");
                    et_pickerPhone.requestFocus();
                    return;
                }
                buyerPickhouse.setPhone(pickerPhone);
                if (selectPickhouseId != null) {
                    buyerPickhouse.setStorehouseId(selectPickhouseId);
                }
                String url = null;
                String data = new String(Base64.encode((new Gson().toJson(buyerPickhouse)).getBytes(), Base64.DEFAULT));
                //url = SXC_API.MODIFY_BUYER_PICK_HOUSE + data;

               // Log.d(EditPickupInfoActivity.class.getName(), url);
                AbHttpUtil httpUtil = AbHttpUtil.getInstance(EditPickupInfoActivity.this);

                AbRequestParams params = new AbRequestParams();
                params.put("data", data);

                httpUtil.post(SXC_API.MODIFY_BUYER_PICK_HOUSE,params,new AbStringHttpResponseListener() {
                    @Override
                    public void onSuccess(int i, String s) {
                        if (!StringUtils.isBlank(s) && "ok".equals(s)) {
                            AbToastUtil.showToast(getApplicationContext(), "编辑提货信息成功");
                            //PickupInfoActivity 需要在onResume中刷新数据，才能显示新的数据
                            finish();
                        } else {
                            AbToastUtil.showToast(getApplicationContext(), "编辑提货信息失败");
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
                        Log.e(EditPickupInfoActivity.class.getName(), s, throwable);
                        AbToastUtil.showToast(getApplicationContext(), "编辑提货信息失败");
                    }
                });
            }
        });

        lv_pickerAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addressAdapter.check((String)view.getTag());
                PickhouseRecord pickhouseRecord = pickhouseList.get(position);
                selectPickhouseId = Integer.parseInt(pickhouseRecord.getId());
            }
        });
    }

    private void setCustomTitle() {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_edit_pickup_info);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(R.string.title_edit_pickup_info);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class PickhouseRecord {
        private String id;
        private String phone;
        private String manager;
        private String areaCode;
        private String name;
        private String address;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


}
