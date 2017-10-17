package com.sxc.natasha.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.ab.view.sliding.AbSlidingPlayView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.BasketCacheManager;
import com.sxc.natasha.common.CollectionUtil;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.BasketItem;
import com.sxc.natasha.domain.ItemModel;
import com.sxc.natasha.domain.ItemSPU;
import com.sxc.natasha.task.BannerUpdateV2Task;
import com.sxc.natasha.ui.AddAndSubView.OnNumChangeListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemDetailActivity extends Activity {
    private ItemModel itemModel = null;
    private Button add2_basket_btn = null;
    private TextView total_fee = null;
    private AddAndSubView andSubView = null;
    public AbSlidingPlayView mSlidingPlayView = null;
    private LayoutInflater mInflater = null;
    private RelativeLayout webContent = null;
    private TextView itemDetailIntroduce = null;
    private TextView itemDetailAttribute = null;
    private TextView productNumberView = null;
    private TextView totalWeightView = null;
    private TextView totalWeightDescView = null;
    private ImageButton reduceNumberButton = null;
    private ImageButton addNumberButton = null;
    private ImageView itemDetailAttributeImg = null;
    private ImageView itemDetailIntroduceImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_item_detail);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        ItemModel itemModel = (ItemModel)getIntent().getSerializableExtra("item");
        if (itemModel == null) {
            Toast.makeText(ItemDetailActivity.this, "商品ID缺失", Toast.LENGTH_LONG).show();
            add2_basket_btn.setEnabled(false);
            return;
        }
        // 设置页面title
        setTitle(itemModel.getItemTitle());
        String itemId = String.valueOf(itemModel.getId());
        //初始化价格信息
        initPage(itemModel);
        //商品图片播放
        showSliding(itemId);
        initView(itemModel);
        getItemSPU(itemId);
        initEvent(itemModel);

    }

    private void initPage(ItemModel itemModel) {
        TextView itemPriceView = (TextView) findViewById(R.id.item_detail_item_price);
        String itemPrice = NumberTool.toYuanNumber(new BigDecimal(itemModel.getItemPrice()).longValue());
        itemPriceView.setText("￥" + itemPrice + "/" + itemModel.getUnit());

        TextView packageWeightView = (TextView) findViewById(R.id.item_detail_package_weight);
        String packageWeight = " (" + itemModel.getPackageWeight() + "斤装)";
        packageWeightView.setText(packageWeight);

        TextView unitPriceView = (TextView) findViewById(R.id.item_detail_unit_price);
        BigDecimal bup = new BigDecimal(itemModel.getItemPrice());
        String unitPrice = NumberTool.toYuanNumber(bup.divide(new BigDecimal(itemModel.getPackageWeight()),2, BigDecimal.ROUND_DOWN).longValue());
        unitPriceView.setText("每斤" + unitPrice + "元");
    }

    private void showSliding(String itemId) {

        mSlidingPlayView = new AbSlidingPlayView(this);
        mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSlidingPlayView.setNavHorizontalGravity(Gravity.CENTER);
        //设置高度
        mSlidingPlayView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
        mSlidingPlayView.startPlay();

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int flag = msg.what;
                List<Bitmap> urlList = (List<Bitmap>) msg.obj;
                updateBannerImg(urlList);
            }
        };

        try {
            String reqestUrl = SXC_API.ITEM_DETAIL_BANNER_URL + itemId;
            Log.i("商品详情", "itemId:" + itemId + ", 图片API：" + reqestUrl);
            BannerUpdateV2Task task = new BannerUpdateV2Task(this, handler, reqestUrl);
            task.execute();
        } catch (Exception e) {
            Log.e("e", e.toString());
        }

        LinearLayout llView = (LinearLayout) findViewById(R.id.slidingPlayViewContent);
        llView.addView(mSlidingPlayView);
    }

    private void updateBannerImg(List<Bitmap> urlList) {
        for (Bitmap imgByte : urlList) {
            final View mPlayView = mInflater.inflate(R.layout.play_view_item, null);
            ImageView mPlayImage = (ImageView) mPlayView.findViewById(R.id.mPlayImage);
            TextView mPlayText = (TextView) mPlayView.findViewById(R.id.mPlayText);
            mPlayText.setVisibility(View.GONE);
            mPlayImage.setImageBitmap(imgByte);
            mSlidingPlayView.addView(mPlayView);
        }
    }

    private void getItemSPU(String itemId) {
        AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
        String url = SXC_API.GET_ITEM_BY_IDS + itemId;
        httpUtil.get(url, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {

                ArrayList<ItemModel> itemModels = new Gson().fromJson(s, new TypeToken<ArrayList<ItemModel>>() {
                }.getType());
                if (!CollectionUtil.isEmpty(itemModels)) {
                    itemModel = itemModels.get(0);
                } else {
                    add2_basket_btn.setEnabled(false);
                    andSubView.setEnabled(false);
                }
            }

            // 失败，调用
            @Override
            public void onFailure(int statusCode, String content,
                                  Throwable error) {
                Toast.makeText(ItemDetailActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }

            // 开始执行前
            @Override
            public void onStart() {
            }


            // 完成后调用，失败，成功
            @Override
            public void onFinish() {
            }
        });
    }


    private void setTitle(String customTitle) {
        TextView title = (TextView) findViewById(R.id.title);
        title.setTextSize(16);
        if (StringUtils.isBlank(customTitle)) {
            title.setText(R.string.title_activity_item_detail);
        } else {
            title.setText(customTitle);
        }
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(ItemModel itemModel) {
        //加入菜篮子
        add2_basket_btn = (Button) findViewById(R.id.add2_basket_btn);
        //商品总价
        total_fee = (TextView) findViewById(R.id.total_fee);
        //商品规格
        itemDetailAttribute = (TextView) findViewById(R.id.item_detail_attribute);
        //商品介绍
        itemDetailIntroduce = (TextView) findViewById(R.id.item_detail_introduce);
        //当前购买数量
        productNumberView = (TextView)findViewById(R.id.product_number);
        //数量 减
        reduceNumberButton = (ImageButton)findViewById(R.id.reduce_number);
        //数量 加
        addNumberButton = (ImageButton)findViewById(R.id.add_number);
        //总重量
        totalWeightView = (TextView)findViewById(R.id.total_weight);
        //单位
        totalWeightDescView = (TextView)findViewById(R.id.total_weight_desc);
        itemDetailAttributeImg = (ImageView)findViewById(R.id.item_detail_attribute_img);
        itemDetailIntroduceImg = (ImageView)findViewById(R.id.item_detail_introduce_img);
        //初始化其他值
        productNumberView.setText("0");
        totalWeightView.setText("0");
        total_fee.setText("￥0.00");
        showWebPage(SXC_API.ITEM_DETAIL_INTRODUCTION_URL+itemModel.getId());
    }

    private void showWebPage(String url){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragment = new WebFragment();
        Bundle params = new Bundle();
        Log.d("showWebPage",url);
        url = url +"&time=" + System.currentTimeMillis();
        params.putString("url", url);
        fragment.setArguments(params);
        transaction.replace(R.id.web_content, fragment);
        transaction.commit();
    }

    private void initEvent(final ItemModel itemModel) {
        // 数量 减
        reduceNumberButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(productNumberView.getText().toString());
                int current = count - 1;
                if (current == 0 || current < 0) {
                    current = 1;
                }
                //更新UI
                productNumberView.setText("" + current);
                long price = new BigDecimal(itemModel.getItemPrice()).longValue();
                //总价
                total_fee.setText(NumberTool.toYuanNumber(price * current));
                //提示框
                totalWeightView.setText("" + (itemModel.getPackageWeight() * current));
            }
        });
        // 数量 加
        addNumberButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(productNumberView.getText().toString());
                int current = count + 1;
                if (current == 0 || current < 0) {
                    current = 1;
                }
                //更新UI
                productNumberView.setText("" + current);
                long price = new BigDecimal(itemModel.getItemPrice()).longValue();
                //总价
                total_fee.setText(NumberTool.toYuanNumber(price * current));
                //提示框
                totalWeightView.setText("" + (itemModel.getPackageWeight() * current));
            }
        });
        // 加入菜篮子
        add2_basket_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(productNumberView.getText().toString());
                if (count == 0) {
                    Toast.makeText(ItemDetailActivity.this, "请选择商品数量", Toast.LENGTH_SHORT).show();
                    return;
                }

                BasketItem basketItem = new BasketItem();
                basketItem.setId(itemModel.getId());
                basketItem.setTitle(itemModel.getItemTitle());
                basketItem.setCount(count);
                basketItem.setPrice(itemModel.getItemPrice());
                BasketCacheManager.addBasketRecord(basketItem,getApplicationContext());

                List<BasketItem> basketItems = BasketCacheManager.getBasket(getApplicationContext());
                if (CollectionUtil.isEmpty(basketItems)) {
                    basketItems = new ArrayList<BasketItem>();
                }
                HomeFragment.totalNum = 0;
                HomeFragment.totalFeeNum = 0;
                for (BasketItem item : basketItems) {
                    HomeFragment.totalFeeNum += item.getPrice() * item.getCount();
                    HomeFragment.totalNum++;
                }
                HomeFragment.totalFeeNum = Float.parseFloat(NumberTool.toYuanNumber((long) HomeFragment.totalFeeNum));
                Toast.makeText(getApplicationContext(), "添加到菜篮子成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //商品规格
        itemDetailAttribute.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDetailIntroduce.setTextColor(getResources().getColor(android.R.color.darker_gray));
                itemDetailIntroduceImg.setVisibility(View.INVISIBLE);
                itemDetailAttribute.setTextColor(getResources().getColor(R.color.light_green));
                itemDetailAttributeImg.setVisibility(View.VISIBLE);

                //showWebPage("https://www.baidu.com");
                Log.d("商品属性API：", SXC_API.ITEM_DETAIL_ATTRIBUTE_URL+itemModel.getId());
                showWebPage(SXC_API.ITEM_DETAIL_ATTRIBUTE_URL+itemModel.getId());
            }
        });
        //商品介绍
        itemDetailIntroduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDetailIntroduce.setTextColor(getResources().getColor(R.color.light_green));
                itemDetailIntroduceImg.setVisibility(View.VISIBLE);
                itemDetailAttribute.setTextColor(getResources().getColor(android.R.color.darker_gray));
                itemDetailAttributeImg.setVisibility(View.INVISIBLE);
                showWebPage(SXC_API.ITEM_DETAIL_INTRODUCTION_URL+itemModel.getId());
            }
        });
    }
}
