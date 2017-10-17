package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.sxc.natasha.common.PhoneNumUtil;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.common.TimeCountUtil;

import java.util.Map;

/**
 * Description: ResetPwdActivity 修改密码Activity
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    ResetPwdActivity.java
 * Create at:   2015-03-18
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-18      zhengshutian    1.0         1.0 Version
 */
public class ResetPwdActivity extends Activity {

    TextView title=null;
    TextView title_back=null;
    String page=null;

    String mobilePhone=null;
    String checkCodeKey=null;

    EditText oldPwdEdit=null;
    EditText newPwdEdit=null;
    EditText checkCodeEdit=null;

    TextView sendCheckCode=null;
    TextView phoneMessage=null;

    private TimeCountUtil timeCountUtil=null;

    Button submitButton=null;

    String userId;
    String oldPwd=null;
    String newPwd=null;
    String checkCode=null;

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
        setContentView(R.layout.reset_pwd_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        page = getIntent().getStringExtra("page");

        title = (TextView) findViewById(R.id.title);
        title.setText(page);

        title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        mobilePhone= UserCache.getUser(getApplicationContext()).getMobilePhone();
        userId = UserCache.getUser(getApplicationContext()).getUserId().toString();

        oldPwdEdit = (EditText) findViewById(R.id.old_pwd_text);
        newPwdEdit = (EditText) findViewById(R.id.new_pwd_text);

        checkCodeEdit = (EditText)findViewById(R.id.check_code_text);

        sendCheckCode = (TextView) findViewById(R.id.send_check_code_text);

        submitButton = (Button) findViewById(R.id.save_button);

        phoneMessage = (TextView) findViewById(R.id.phoneMessage);

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
                finish();
            }
        });

        sendCheckCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneMessage.setText(PhoneNumUtil.buildPhoneMessage(mobilePhone));

                timeCountUtil.start();//开始计时

                sendCheckCode();

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oldPwd = oldPwdEdit.getText().toString().trim();
                newPwd = newPwdEdit.getText().toString().trim();
                checkCode = checkCodeEdit.getText().toString().trim();

                if(StringUtils.isBlank(oldPwd)){
                    Toast.makeText(ResetPwdActivity.this, "原密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(StringUtils.isBlank(newPwd)){
                    Toast.makeText(ResetPwdActivity.this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(StringUtils.isBlank(checkCode)){
                    Toast.makeText(ResetPwdActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(checkCodeKey == null){
                    Toast.makeText(ResetPwdActivity.this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    doResetPwd();
                }
            }
        });

    }


    /**
     * 发送验证码
     *
     * @author zhengshutian
     *
     */
    private void doResetPwd(){

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(ResetPwdActivity.this);

        AbRequestParams params = new AbRequestParams();

        params.put("userId", userId);

        params.put("oldPwd", oldPwd);
        params.put("newPwd", newPwd);

        params.put("codeKey", checkCodeKey);

        params.put("codeValue",checkCode);

        httpUtil.post(SXC_API.POST_RESETPWD, params, new AbStringHttpResponseListener() {

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d("CheckCodeActivity", "onSuccess");

                if (content.equals("0")) {
                    Toast.makeText(ResetPwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ResetPwdActivity.this,MainActivity.class);
                    intent.putExtra("pageId",R.id.id_tab_usercenter);
                    startActivity(intent);

                } else if (content.equals("1")) {
                    Toast.makeText(ResetPwdActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                } else if (content.equals("2")) {
                    Toast.makeText(ResetPwdActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ResetPwdActivity.this, "原密码不一致", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ResetPwdActivity.this, "服务器出错", Toast.LENGTH_SHORT).show();
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

        AbHttpUtil httpUtil= AbHttpUtil.getInstance(ResetPwdActivity.this);

        AbRequestParams params = new AbRequestParams();

        params.put("mobilePhone", mobilePhone);
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

                    Toast.makeText(ResetPwdActivity.this, "短信发送成功", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ResetPwdActivity.this, "短信发送失败", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ResetPwdActivity.this, "服务器出错", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.d("CheckCodeActivity", "onFinish");
            };

        });
    }


}
