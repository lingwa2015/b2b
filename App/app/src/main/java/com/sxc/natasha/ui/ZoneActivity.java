package com.sxc.natasha.ui;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.IPData;
import com.sxc.natasha.domain.User;

import java.net.URLEncoder;


/**
 * Description: ZoneActivity 地区Activity
 *
 * @author :     zhengshutian
 * @version :    1.0
 * Filename:    ZoneActivity.java
 * Create at:   2015-03-20
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-20      zhengshutian    1.0         1.0 Version
 */
public class ZoneActivity extends Activity{

    TextView title=null;
    TextView title_back=null;
    String page=null;

    TextView cityButton = null;

    String userId=null;
    String areaCode=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //统一绑定view
        findView();

        //统一绑定Listener
        setClickListener();

        //通过IP定位
        getLocationByIP();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 通过IP定位成功后，修改用户定位
     *
     * @author zhengshutian
     *
     */
    private void updateUserLocation(){

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(ZoneActivity.this);


        AbRequestParams params = new AbRequestParams();
        try {
            params.put("areaCode", URLEncoder.encode(areaCode, "UTF-8"));
        }catch(Exception e){

        }

        params.put("userId", userId);

        httpUtil.post(SXC_API.POST_USER_INFO, params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d("updateUserAreaCode", "onSuccess");

                User user = UserCache.getUser(getApplicationContext());
                user.setAreaCode(areaCode);

                Gson gson = new Gson();
                UserCache.saveUser("",getApplicationContext());
                UserCache.saveUser(gson.toJson(user),getApplicationContext());

            };

            @Override
            public void onStart() {
                Log.d("updateUserAreaCode", "onStart");
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                Log.d("updateUserAreaCode", "onFailure");
                Log.d("updateUserAreaCode", statusCode+"");
            }

            @Override
            public void onFinish() {
                Log.d("updateUserAreaCode", "onFinish");
            };

        });

    }

    /**
     * 通过IP定位
     *
     * @author zhengshutian
     *
     */
    private void getLocationByIP(){

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(ZoneActivity.this);

        AbRequestParams params = new AbRequestParams();

        params.put("ak",SXC_API.BAIDU_OPEN_API_AK);

        httpUtil.get(SXC_API.BAIDU_OPEN_API_LOCATION,params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d("ZoneActivity", content);

                Gson gson = new Gson();

                java.lang.reflect.Type type = new TypeToken<IPData>() {}.getType();

                IPData locationData = gson.fromJson(content, type);

                StringBuilder sb = new StringBuilder();

                if(!StringUtils.isBlank(locationData.getContent().getAddress_detail().getProvince())){
                    sb.append(locationData.getContent().getAddress_detail().getProvince());
                }
                if(!StringUtils.isBlank(locationData.getContent().getAddress_detail().getCity())){
                    sb.append(",");
                    sb.append(locationData.getContent().getAddress_detail().getCity());
                }

                if(!StringUtils.isBlank(locationData.getContent().getAddress_detail().getDistrict())){
                    sb.append(",");
                    sb.append(locationData.getContent().getAddress_detail().getDistrict());
                }
                areaCode=sb.toString();
                cityButton.setText(sb.toString());

                //修改服务端用户区域
                updateUserLocation();
            }

            @Override
            public void onStart() {
                Log.d("ZoneActivity", "onStart");
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                Log.d("ZoneActivity", "onFailure");
                Log.d("ZoneActivity", statusCode + "");
                Toast.makeText(ZoneActivity.this, "自动定位失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.d("ZoneActivity", "onFinish");
            }

        });
    }
    /**
     * 绑定view
     *
     * @author zhengshutian
     *
     */
    private void findView(){

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.zone_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        page = getIntent().getStringExtra("page");

        title = (TextView) findViewById(R.id.title);
        title.setText(page);

        title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        cityButton =(TextView) findViewById(R.id.location_button);

        userId = UserCache.getUser(getApplicationContext()).getUserId().toString();

    }

    /**
     * 绑定Listener
     *
     * @author zhengshutian
     *
     */
    private void setClickListener(){

        //back
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
