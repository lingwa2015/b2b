package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Intent;
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
import com.sxc.natasha.common.TimeCountUtil;
import com.sxc.natasha.domain.User;

import java.util.Map;

/**
 * Description: UpdatePhoneActivity 验证并更新 新手机号Activity
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    UpdatePhoneActivity.java
 * Create at:  2015-03-25
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-25      zhengshutian    1.0         1.0 Version
 */
public class UpdatePhoneActivity extends Activity {

    TextView title=null;
    TextView title_back=null;
    String page=null;


    EditText newPhoneEdit=null;
    EditText checkCodeEdit=null;
    TextView sendCheckCode=null;

    private TimeCountUtil timeCountUtil=null;

    Button submitButton=null;

    String userId;
    String checkCode=null;
    String newPhone=null;
    String checkCodeKey=null;


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
        setContentView(R.layout.update_phone_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        page = getIntent().getStringExtra("page");

        title = (TextView) findViewById(R.id.title);
        title.setText(page);

        title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        userId = UserCache.getUser(getApplicationContext()).getUserId().toString();

        newPhoneEdit = (EditText) findViewById(R.id.new_phone_text);

        checkCodeEdit = (EditText)findViewById(R.id.check_code_text);

        sendCheckCode = (TextView) findViewById(R.id.send_check_code_text);

        submitButton = (Button) findViewById(R.id.save_button);

        timeCountUtil = new TimeCountUtil(60000, 1000 , sendCheckCode);

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

                Intent intent = new Intent(UpdatePhoneActivity.this,MyInfoActivity.class);
                intent.putExtra("pageId",R.id.id_tab_usercenter);
                startActivity(intent);

            }
        });

        sendCheckCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newPhone= newPhoneEdit.getText().toString().trim();

                if(StringUtils.isBlank(newPhone)){
                    Toast.makeText(UpdatePhoneActivity.this, "请输入新手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    sendCheckCode();  //发送验证码

                    timeCountUtil.start();//开始计时
                }

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newPhone = newPhoneEdit.getText().toString().trim();
                checkCode = checkCodeEdit.getText().toString().trim();

                if(StringUtils.isBlank(newPhone)){
                    Toast.makeText(UpdatePhoneActivity.this, "新手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(StringUtils.isBlank(checkCode)){
                    Toast.makeText(UpdatePhoneActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(checkCodeKey==null){
                    Toast.makeText(UpdatePhoneActivity.this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    doUpdatePhone();
                }

            }
        });

    }


    /**
     * 执行更新手机号
     *
     * @author zhengshutian
     *
     */
    private void doUpdatePhone(){

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(UpdatePhoneActivity.this);

        AbRequestParams params = new AbRequestParams();

        params.put("userId", userId);

        params.put("newPhone", newPhone);

        params.put("codeKey", checkCodeKey);

        params.put("codeValue",checkCode);

        httpUtil.post(SXC_API.POST_VALIDATE_NEW_PHONE, params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d("CheckCodeActivity", "onSuccess");

                if (content.equals("0")) {

                    User user = UserCache.getUser(getApplicationContext());
                    user.setMobilePhone(newPhone);

                    Gson gson = new Gson();
                    UserCache.saveUser("",getApplicationContext());
                    UserCache.saveUser(gson.toJson(user),getApplicationContext());

                    Toast.makeText(UpdatePhoneActivity.this, "手机号修改成功", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(UpdatePhoneActivity.this,MyInfoActivity.class);
                    intent.putExtra("pageId",R.id.id_tab_usercenter);
                    startActivity(intent);

                } else if (content.equals("1")) {
                    Toast.makeText(UpdatePhoneActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                } else if (content.equals("2")) {
                    Toast.makeText(UpdatePhoneActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdatePhoneActivity.this, "该手机号已被注册", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onStart() {
                Log.d("CheckCodeActivity", "onStart");
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                Log.d("CheckCodeActivity", "onFailure");
                Log.d("CheckCodeActivity", statusCode + "");
                Toast.makeText(UpdatePhoneActivity.this, "服务器出错", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.d("CheckCodeActivity", "onFinish");

            }

        });

    }

    /**
     * 发送验证码
     *
     * @author zhengshutian
     *
     */
    private void sendCheckCode(){

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(UpdatePhoneActivity.this);

        AbRequestParams params = new AbRequestParams();

        params.put("mobilePhone", newPhone);
        params.put("userId", userId);

        httpUtil.post(SXC_API.POST_CHECKCODE, params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d("CheckCodeActivity", "onSuccess");
                Log.d("content", content);

                if(statusCode== 200) {
                    Map<String, Object> map = new Gson().fromJson(content, Map.class);
                    checkCodeKey = (String)map.get("codeKey");
                    Log.d("checkCodeKey", checkCodeKey);

                    Toast.makeText(UpdatePhoneActivity.this, "短信发送成功", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(UpdatePhoneActivity.this, "短信发送失败", Toast.LENGTH_SHORT).show();
            };

            @Override
            public void onStart() {
                Log.d("CheckCodeActivity", "onStart");
            }

            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                Log.d("CheckCodeActivity", "onFailure");
                Log.d("CheckCodeActivity", statusCode+"");
                Toast.makeText(UpdatePhoneActivity.this, "服务器出错", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.d("CheckCodeActivity", "onFinish");
            };

        });
    }


}
