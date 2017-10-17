package com.sxc.natasha.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.ab.http.AbHttpResponseListener;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.BannerUtil;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.User;
import com.sxc.natasha.ui.MainActivity;
import com.sxc.natasha.ui.R;

import java.lang.reflect.Type;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
            Button submitBtn = (Button)findViewById(R.id.login_submit_btn);
            /*final ImageView bannerIv = (ImageView)findViewById(R.id.test_banner);
            new Thread(){
                @Override
                public void run() {
                    try{
                        super.run();
                        bannerIv.setImageBitmap(BannerUtil.getBannerImg("http://112.124.116.88/banner/banner.htm?name=banner3.jpg"));
                    }catch (Exception e){
                        Log.e("e",e.toString());
                    }


                }
            }.start();
*/


            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView mobilePhoneTv = (TextView)findViewById(R.id.login_mobilePhone);
                    TextView passWordTv = (TextView)findViewById(R.id.login_password);

                    if(StringUtils.isEmpty(mobilePhoneTv.getText())){
                        AbToastUtil.showToast(getApplicationContext(), "请填写您的手机号码");
                        return;
                    }

                    if(StringUtils.isEmpty(passWordTv.getText())){
                        AbToastUtil.showToast(getApplicationContext(), "请输入密码");
                        return;

                    }

                    String mobilepPhone = mobilePhoneTv.getText().toString();
                    String password = passWordTv.getText().toString();
                    AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplication());

                    String loginUrl = SXC_API.GET_LOGIN.replace("@mp",mobilepPhone).replace("@ps",password);

                    httpUtil.get(loginUrl,new AbStringHttpResponseListener() {
                        @Override
                        public void onSuccess(int i, String s) {

                            if("1".equals(s)){
                                AbToastUtil.showToast(getApplicationContext(), "用户名或密码不对,请再试试");
                                return;
                            }

                            UserCache.saveUser(s,getApplicationContext());

                            try{
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplication().startActivity(intent);

                            }catch (Exception e){
                                Log.e("start new ac :",e.getMessage());
                            }
                        }

                        @Override
                        public void onStart() {
                            Log.d(getClass().getName(), "调用了OnStart.");
                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onFailure(int i, String s, Throwable throwable) {
                            AbToastUtil.showToast(getApplicationContext(), "抱歉，出错了！异常:" + s);
                        }
                    });



                }
            });
        }catch(Exception e){
            Log.e("e",e.toString());
        }

        /**
         *   Intent intent = new Intent(activity, PayOrderActivity.class);
         intent.putExtra("page", "立即支付");
         intent.putExtra("userId", order.getBuyerId());
         intent.putExtra("orderNo", order.getOrderNo());
         intent.putExtra("money", NumberTool.toYuanNumber(order.getTotalFee()));
         activity.startActivity(intent);
         */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
