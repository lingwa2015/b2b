package com.sxc.natasha.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.google.gson.Gson;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.User;

import java.net.URLEncoder;

/**
 * Description: UpdateNameActivity 修改姓名Activity
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    UpdateNameActivity.java
 * Create at:   2015-03-17
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-16      zhengshutian    1.0         1.0 Version
 */
public class UpdateNameActivity extends Activity{

    TextView title=null;
    TextView title_back=null;
    String page=null;

    EditText nameEditText=null;//姓名输入框

    Button saveButton=null;//保存按钮

    String userId=null;   //用户id

    String userName=null; //用户姓名

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //统一绑定view
        findView();

        //统一绑定Listener
        setClickListener();

    }

    /**
     * 绑定view
     *
     * @author zhengshutian
     *
     */
    private void findView(){

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.update_name_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        page = getIntent().getStringExtra("page");

        title = (TextView) findViewById(R.id.title);
        title.setText(page);

        title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        //姓名输入框
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        nameEditText.setText(UserCache.getUser(getApplicationContext()).getUserName());

        //保存按钮
        saveButton = (Button) findViewById(R.id.save_button);

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

        //修改姓名
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                userName = nameEditText.getText().toString().trim();

                if(!StringUtils.isBlank(userName)){
                    doUpdateName();
                }
                else{
                    Toast.makeText(UpdateNameActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 执行更新操作
     *
     * @author zhengshutian
     *
     */
    private void doUpdateName(){

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(UpdateNameActivity.this);

        AbRequestParams params = new AbRequestParams();

        try {
            params.put("userName", URLEncoder.encode(userName, "UTF-8"));
        }catch(Exception e){

        }
        params.put("userId", userId);

        httpUtil.post(SXC_API.POST_USER_INFO, params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d("UpdateNameActivity", "onSuccess");

                User user = UserCache.getUser(getApplicationContext());
                user.setUserName(nameEditText.getText().toString().trim());

                Gson gson = new Gson();
                UserCache.saveUser("",getApplicationContext());
                UserCache.saveUser(gson.toJson(user),getApplicationContext());

                Toast.makeText(UpdateNameActivity.this, "姓名修改成功", Toast.LENGTH_SHORT).show();

            };

            @Override
            public void onStart() {
                Log.d("UpdateNameActivity", "onStart");
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                Log.d("UpdateNameActivity", "onFailure");
                Log.d("UpdateNameActivity", statusCode+"");
                Toast.makeText(UpdateNameActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.d("UpdateNameActivity", "onFinish");
            };

        });

    }
}
