package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.PhoneNumUtil;

/**
 * Description: MyInfoActivity 个人信息Activity
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    MyInfoActivity.java
 * Create at:   2015-03-16
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-16      zhengshutian    1.0         1.0 Version
 */
public class MyInfoActivity extends Activity {

    TextView title=null;
    TextView title_back=null;
    String page=null;

    private RelativeLayout nameLayout = null;    //姓名布局
    private RelativeLayout zoneLayout = null;    //地区布局
    private RelativeLayout phoneLayout = null;   //手机布局

    TextView name=null;
    TextView zone=null;
    TextView phone=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.my_info_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        //统一绑定view
        findView();

        //统一绑定Listener
        setClickListener();

    }

    @Override
    protected void onResume() {

        super.onResume();

        name = (TextView) findViewById(R.id.name);
        name.setText(UserCache.getUser(getApplicationContext()).getUserName());

        zone =(TextView) findViewById(R.id.zone);
        zone.setText(UserCache.getUser(getApplicationContext()).getAreaCode());

        phone = (TextView) findViewById(R.id.phone);
        phone.setText(PhoneNumUtil.hidePhoneNum(UserCache.getUser(getApplicationContext()).getMobilePhone()));

    }

    /**
     * 绑定view
     *
     * @author zhengshutian
     *
     */
    private void findView(){

        page = getIntent().getStringExtra("page");

        title = (TextView) findViewById(R.id.title);
        title.setText(page);

        title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        //姓名
        nameLayout = (RelativeLayout) findViewById(R.id.name_layout);
        //地区
        zoneLayout = (RelativeLayout) findViewById(R.id.zone_layout);
        //手机
        phoneLayout = (RelativeLayout) findViewById(R.id.phone_layout);

        name = (TextView) findViewById(R.id.name);
        name.setText(UserCache.getUser(getApplicationContext()).getUserName());

        zone =(TextView) findViewById(R.id.zone);
        zone.setText(UserCache.getUser(getApplicationContext()).getAreaCode());

        phone = (TextView) findViewById(R.id.phone);
        phone.setText(UserCache.getUser(getApplicationContext()).getMobilePhone());
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

                Intent intent = new Intent(MyInfoActivity.this,MainActivity.class);
                intent.putExtra("pageId",R.id.id_tab_usercenter);
                startActivity(intent);

            }
        });

        //修改姓名
        nameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this,UpdateNameActivity.class);
                intent.putExtra("page","姓名");
                startActivity(intent);
            }
        });

        //地区
        zoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this,ZoneActivity.class);
                intent.putExtra("page","地区");
                startActivity(intent);
            }
        });

        //修改手机
        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this,ValidatePhoneActivity.class);
                intent.putExtra("page","验证原手机号");
                startActivity(intent);
            }
        });

    }
}
