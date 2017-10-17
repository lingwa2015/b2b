package com.sxc.natasha.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.domain.User;
import com.sxc.natasha.ui.login.Login;


public class MainActivity extends Activity implements View.OnClickListener {
    private LinearLayout mTabHome = null;
    private LinearLayout mTabBasket = null;
    private LinearLayout mTabMyorder = null;
    private LinearLayout mTabWallet = null;
    private LinearLayout mTabUsercenter = null;

    private ImageButton mBtnHome = null;
    private ImageButton mBtnBasket = null;
    private ImageButton mBtnMyorder = null;
    private ImageButton mBtnWallet = null;
    private ImageButton mBtnUsercenter = null;

    private TextView mTextHome = null;
    private TextView mTextBasket = null;
    private TextView mTextMyorder = null;
    private TextView mTextWallet = null;
    private TextView mTextUsercenter = null;

    private Fragment mFragmentHome = null;
    private Fragment mFragmentBasket = null;
    private Fragment mFragmentMyorder = null;
    private Fragment mFragmentWallet = null;
    private Fragment mFragmentUsercenter = null;

    private TextView title = null;

    private RelativeLayout animLayout = null;
    private ImageView animImage = null;
    private Animation animation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        try{
            User user = UserCache.getUser(getApplicationContext());
            if(user == null){
                Intent intent = new Intent(getApplication(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                getApplication().startActivity(intent);
                return;
            }

        }catch (Exception e){
            Log.e("start Act",e.getMessage());
        }

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
        //设置标题为某个layout
        initView();
        int pageId = getIntent().getIntExtra("pageId",R.id.id_tab_home);
        initEvent();
        setOnClick(pageId);



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {

        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabBasket = (LinearLayout) findViewById(R.id.id_tab_basket);
        mTabMyorder = (LinearLayout) findViewById(R.id.id_tab_myorder);
        mTabWallet = (LinearLayout) findViewById(R.id.id_tab_wallet);
        mTabUsercenter = (LinearLayout) findViewById(R.id.id_tab_usercenter);

        mBtnHome = (ImageButton) findViewById(R.id.id_tab_home_img);
        mBtnBasket = (ImageButton) findViewById(R.id.id_tab_basket_img);
        mBtnMyorder = (ImageButton) findViewById(R.id.id_tab_myorder_img);
        mBtnWallet = (ImageButton) findViewById(R.id.id_tab_wallet_img);
        mBtnUsercenter = (ImageButton) findViewById(R.id.id_tab_usercenter_img);

        mTextHome = (TextView) findViewById(R.id.id_tab_home_text);
        mTextBasket = (TextView) findViewById(R.id.id_tab_basket_text);
        mTextMyorder = (TextView) findViewById(R.id.id_tab_myorder_text);
        mTextWallet = (TextView) findViewById(R.id.id_tab_wallet_text);
        mTextUsercenter = (TextView) findViewById(R.id.id_tab_usercenter_text);

        title = (TextView) findViewById(R.id.title);

        animImage = (ImageView) findViewById(R.id.anim_img);
        animLayout = (RelativeLayout) findViewById(R.id.anim_layout);
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.addbasket_animator);
    }

    private void initEvent() {
        mTabHome.setOnClickListener(this);
        mTabBasket.setOnClickListener(this);
        mTabMyorder.setOnClickListener(this);
        mTabWallet.setOnClickListener(this);
        mTabUsercenter.setOnClickListener(this);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        setOnClick(v.getId());
    }

    public void setOnClick(int id){
        resetImgs();
        switch (id) {
            case R.id.id_tab_home:
                if (mFragmentHome == null) {
                    mFragmentHome = new HomeFragment();
                }
                setSelect(mFragmentHome, mBtnHome, mTextHome, mTabHome, "首页");
                break;
            case R.id.id_tab_basket:
                if (mFragmentBasket == null) {
                    mFragmentBasket = new BasketFragment();
                }
                setSelect(mFragmentBasket, mBtnBasket, mTextBasket, mTabBasket, "菜篮子");
                break;
            case R.id.id_tab_myorder:
                if (mFragmentMyorder == null) {
                    mFragmentMyorder = new MyorderFragment();
                }
                setSelect(mFragmentMyorder, mBtnMyorder, mTextMyorder, mTabMyorder, "我的订单");
                break;
            case R.id.id_tab_wallet:
                if (mFragmentWallet == null) {
                    mFragmentWallet = new WalletFragment();
                }
                setSelect(mFragmentWallet, mBtnWallet, mTextWallet, mTabWallet, "我的钱包");
                break;
            case R.id.id_tab_usercenter:
                if (mFragmentUsercenter == null) {
                    mFragmentUsercenter = new UsercenterFragment();
                }
                setSelect(mFragmentUsercenter, mBtnUsercenter, mTextUsercenter, mTabUsercenter, "个人中心");
                break;
        }
    }

    private void setSelect(Fragment fragment, ImageButton mBtn, TextView mText, LinearLayout mTab, String titleStr) {

        if(titleStr.equals("首页")){
            mBtn.setBackground(getResources().getDrawable(R.drawable.home_click));
        }
        else if(titleStr.equals("菜篮子")){
            mBtn.setBackground(getResources().getDrawable(R.drawable.basket_click));
        }
        else if(titleStr.equals("我的订单")){
            mBtn.setBackground(getResources().getDrawable(R.drawable.myorder_click));
        }
        else if(titleStr.equals("我的钱包")){
            mBtn.setBackground(getResources().getDrawable(R.drawable.wallet_click));
        }
        else{
            mBtn.setBackground(getResources().getDrawable(R.drawable.usercenter_click));
        }



        mText.setTextColor(getResources().getColor(R.color.white));
        mTab.setBackgroundColor(getResources().getColor(R.color.transparent_bg));
        title.setText(titleStr);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }

    private void resetImgs() {
        mBtnHome.setBackground(getResources().getDrawable(R.drawable.home));
        mBtnBasket.setBackground(getResources().getDrawable(R.drawable.basket));
        mBtnMyorder.setBackground(getResources().getDrawable(R.drawable.myorder));
        mBtnWallet.setBackground(getResources().getDrawable(R.drawable.wallet));
        mBtnUsercenter.setBackground(getResources().getDrawable(R.drawable.usercenter));

        mTextHome.setTextColor(getResources().getColor(R.color.black));
        mTextBasket.setTextColor(getResources().getColor(R.color.black));
        mTextMyorder.setTextColor(getResources().getColor(R.color.black));
        mTextWallet.setTextColor(getResources().getColor(R.color.black));
        mTextUsercenter.setTextColor(getResources().getColor(R.color.black));

        mTabHome.setBackgroundColor(getResources().getColor(R.color.white));
        mTabBasket.setBackgroundColor(getResources().getColor(R.color.white));
        mTabMyorder.setBackgroundColor(getResources().getColor(R.color.white));
        mTabUsercenter.setBackgroundColor(getResources().getColor(R.color.white));
        mTabWallet.setBackgroundColor(getResources().getColor(R.color.white));
    }


    private long mExitTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            animImage.startAnimation(animation);
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再次点击返回退出!", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
