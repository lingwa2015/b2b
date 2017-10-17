package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.BasketCacheManager;
import com.sxc.natasha.common.CollectionUtil;
import com.sxc.natasha.common.DateUtil;
import com.sxc.natasha.common.ListUtils;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.BasketItem;
import com.sxc.natasha.domain.Order;
import com.sxc.natasha.domain.OrderSKU;
import com.sxc.natasha.domain.User;
import com.sxc.natasha.task.GetDiscountTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoOrderActivity extends Activity {

    private String storeHouseId = null;

    private Long discount = 0l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomTitle();
        final User user = UserCache.getUser(getApplicationContext());
        //设置系统结算通知
        TextView noticeView = (TextView) findViewById(R.id.system_notice_do_order);
        Map<String, Integer> diffMap = DateUtil.getDifferentHoursAndMinutes(new Date(), "20:00:00");
        if (diffMap.get("diffHours") <= 0 && diffMap.get("diffMinutes") <= 0) {
            noticeView.setText("今天结算已经完毕！");
        } else {
            noticeView.setText("距离今天最后结算时间还剩" + diffMap.get("diffHours") + "小时" + diffMap.get("diffMinutes") + "分钟");
        }

        // 这个 就是用户从提货信息列表 选中的 小仓id
        storeHouseId = getIntent().getStringExtra("storeHouseId");
        Log.d(getClass().getName(), "Name:" + user.getUserName() + ", PhoneNumber:"
                + user.getMobilePhone() + ", storeHouseId:" + storeHouseId);
        loadData(user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<BasketItem> cacheItems = BasketCacheManager.getBasket(getApplicationContext());
        Log.i("onResume菜篮子数据:", "size of cacheItems:" + cacheItems.size());
        if (ListUtils.isEmpty(cacheItems)) {
            AbToastUtil.showToast(getApplicationContext(), "订单已经提交，请继续完成支付!");
            Button orderBtn = (Button) findViewById(R.id.do_order_button);
            //orderBtn.setText("查看订单");
            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DoOrderActivity.this, MainActivity.class);
                    intent.putExtra("pageId", R.id.id_tab_myorder);
                    startActivity(intent);
                }
            });
        }
    }

    private void setCustomTitle() {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_do_order);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(R.string.title_activity_do_order);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData(final User user) {
        if (TextUtils.isEmpty(user.getMobilePhone())) {
            Log.w(this.getClass().getName(), "提货人手机号码为必需参数");
            return;
        }

        AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
        httpUtil.get(SXC_API.GET_STORE_HOUSE_BY_PHONE + user.getMobilePhone(), new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                Log.d("in loadPickupInfo", s);

                Type t = new TypeToken<Map<String, List<Map<String, String>>>>() {
                }.getType();
                Gson gson = new Gson();
                Map<String, List<Map<String, String>>> resultMap = gson.fromJson(s, t);
                //提货信息
                loadPickupInfo(user.getMobilePhone(), resultMap);

                //加载购物车数据
                List<BasketItem> cacheItems = BasketCacheManager.getBasket(getApplicationContext());
                Log.i("菜篮子数据:", "size of cacheItems:" + cacheItems.size());
                if (ListUtils.isEmpty(cacheItems)) {
                    AbToastUtil.showToast(getApplicationContext(), "对不起，菜篮子中没有商品，请返回添加!");
                    return;
                }
                //计算总费用
                long totalFee = accumulateTotalFee(cacheItems);
                doCreateOrder(totalFee, user, cacheItems, resultMap);
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

    private void loadPickupInfo(final String phoneNumber, final Map<String, List<Map<String, String>>> resultMap) {
        if (resultMap == null) return;

        List<Map<String, String>> pickupInfos = resultMap.get(phoneNumber);
        if (CollectionUtil.isEmpty(pickupInfos)) return;
        Map<String, String> defaultAddress = null;
        for (Map<String, String> map : pickupInfos) {
            if (!StringUtils.isBlank(storeHouseId)) {
                if (map.get("id").equalsIgnoreCase(storeHouseId)) {
                    defaultAddress = map;
                    break;
                }
            } else {
                if (map.get("defaultPickHouse").equalsIgnoreCase("1")) {
                    defaultAddress = map;
                    break;
                }
            }
        }
        if (defaultAddress == null) {
            defaultAddress = pickupInfos.get(0);//如果没有一个默认地址，取第一个作为默认取货地址
        }
        TextView pickerNameView = (TextView) findViewById(R.id.do_order_picker_name);
        pickerNameView.setText(defaultAddress.get("pickupName"));

        TextView pickerPhoneView = (TextView) findViewById(R.id.do_order_picker_phone);
        pickerPhoneView.setText(defaultAddress.get("pickupPhone"));

        TextView storeHouseNameView = (TextView) findViewById(R.id.do_order_picker_storehouse_name);
        storeHouseNameView.setText(defaultAddress.get("storeHouseName"));

        TextView storeHouseAddressView = (TextView) findViewById(R.id.do_order_picker_storehouse_addr);
        storeHouseAddressView.setText(defaultAddress.get("storeHouseAddress"));

        //添加提货信息的点击事件
        final  Map<String, String> editMap = defaultAddress;
        View editPickAddressView = findViewById(R.id.edit_default_pickup_address);
        editPickAddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AbToastUtil.showToast(getApplicationContext(), "开始编辑!");
                Intent intent = new Intent(DoOrderActivity.this, PickupInfoActivity.class);
                intent.putExtra("id", editMap.get("id"));
                intent.putExtra("userId", 162);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });

        //仓管人员信息
        TextView storeHouseManagerView = (TextView) findViewById(R.id.do_order_sh_manager_name);
        storeHouseManagerView.setText(defaultAddress.get("storeHouseManager"));

        TextView managerPhoneView = (TextView) findViewById(R.id.do_order_sh_manager_phone);
        managerPhoneView.setText(defaultAddress.get("managerPhone"));

        TextView pickTimeNoticeView = (TextView) findViewById(R.id.do_order_picker_time_notice);
        Date newDate = DateUtil.add(new Date(), Calendar.DAY_OF_MONTH, 1);
        String pickDate = DateUtil.getTimeByFromat(newDate, DateUtil.CN_TIME_FORMAT_SEPERATE);
        pickTimeNoticeView.setText("现在付款可在 " + pickDate + " 上午5点-晚上20点 提货");
    }

    private long accumulateTotalFee(List<BasketItem> cacheItems) {

        Long lSpuTotal = 0L, lDiscount = 0L, lTotalFee;

        for (BasketItem bi : cacheItems) {
            BigDecimal bprice = new BigDecimal(bi.getPrice());
            lSpuTotal += bprice.multiply(new BigDecimal(bi.getCount())).longValue();
        }
        lTotalFee = lSpuTotal - lDiscount;
        //TODO 优惠数据暂时不管(需要通过接口实时拿)，暂时默认 0

        TextView spuTotalFeeView = (TextView) findViewById(R.id.do_order_spu_total_fee);
        spuTotalFeeView.setText("￥" + NumberTool.toYuanNumber(lSpuTotal));

        TextView discountView = (TextView) findViewById(R.id.do_order_discount);
        discountView.setTextColor(getResources().getColor(R.color.red));
        discountView.setText("-￥" + NumberTool.toYuanNumber(lDiscount));

        return lTotalFee;
    }

    private void updateDiscount(Long discount,Long totalFee){
        TextView tv = (TextView)findViewById(R.id.do_order_discount);
        TextView orderTotalFeeView = (TextView) findViewById(R.id.do_order_total_fee);
        if(discount == 0l){
            tv.setText("￥ 0.0");
            orderTotalFeeView.setText("￥" + NumberTool.toYuanNumber(totalFee));

        }else{
            tv.setText("￥"+NumberTool.toYuanNumber(totalFee - discount));
            orderTotalFeeView.setText("￥" + NumberTool.toYuanNumber(discount));

        }

        this.discount = discount;

    }

    private void doCreateOrder(final long totalFee, final User user, final List<BasketItem> cartItems, Map<String, List<Map<String, String>>> resultMap) {

        if (resultMap == null) return;

        List<Map<String, String>> pickupInfos = resultMap.get(user.getMobilePhone());
        if (CollectionUtil.isEmpty(pickupInfos)) return;
        Map<String, String> defaultAddress = null;

        for (Map<String, String> map : pickupInfos) {
            if (!StringUtils.isBlank(storeHouseId)) {
                if (map.get("id").equalsIgnoreCase(storeHouseId)) {
                    defaultAddress = map;
                    break;
                }
            } else {
                if (map.get("defaultPickHouse").equalsIgnoreCase("1")) {
                    defaultAddress = map;
                    break;
                }
            }
        }

        Integer skuTotalNum = 0;
        for(BasketItem bi : cartItems){
            skuTotalNum += bi.getCount();

        }

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 100){
                    updateDiscount((Long)msg.obj,totalFee);
                }
            }
        };


        try{
            GetDiscountTask task = new GetDiscountTask(this,handler,skuTotalNum,totalFee);
            task.execute();
        }catch (Exception e){
            Log.e("", "", e);
        }

        //如果没有默认的提货仓，选择第一个
        if (defaultAddress == null) {
            defaultAddress = pickupInfos.get(0);
        }
        final Map<String, String> defaultInfoMap = defaultAddress;
        Button doOrderBtn = (Button) findViewById(R.id.do_order_button);
        doOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final  Order order = new Order();
                try {
                    //组装订单信息
                    order.setBuyerId(user.getId());
                    order.setBuyerMobilePhone(user.getMobilePhone());
                    order.setBuyerName(user.getUserName());

                    //提货时间不能有 时分秒
                    Date pickTime = DateUtil.add(new Date(), Calendar.DAY_OF_MONTH, 1);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(pickTime);
                    cal.set(Calendar.HOUR_OF_DAY,0);
                    cal.set(Calendar.MINUTE,0);
                    cal.set(Calendar.SECOND,0);

                    order.setPicktime(cal.getTime());
                    order.setOperatorUserId(user.getId());
                    //提货仓信息
                    order.setPickhouseId(Integer.parseInt(defaultInfoMap.get("storeHouseId")));

                    order.setBuyerPickhouseId(Integer.parseInt(defaultInfoMap.get("id")));
                    order.setPickStoreHouseName(defaultInfoMap.get("storeHouseName"));
                    order.setPickStoreHouseAddress(defaultInfoMap.get("storeHouseAddress"));
                    order.setPickStoreHouseManagerName(defaultInfoMap.get("storeHouseManager"));
                    order.setPickStoreHouseManagerPhone(defaultInfoMap.get("managerPhone"));
                    order.setOrderStatus(1);
                    order.setState(1);
                    if(discount == 0l){
                        order.setDiscountedFee(totalFee);
                    }else{
                        order.setDiscountedFee(discount);
                    }
                    order.setTotalFee(totalFee);
                    List<OrderSKU> orderSKUs = new ArrayList<OrderSKU>();

                    for (BasketItem basketItem : cartItems) {
                        BigDecimal bPrice = new BigDecimal(basketItem.getPrice());
                        OrderSKU orderSKU = new OrderSKU();
                        orderSKU.setItemId(basketItem.getId());
                        //orderSKU.setItemName(map.get("title"));
                        orderSKU.setState(1);
                        orderSKU.setFee(bPrice.multiply(new BigDecimal(basketItem.getCount())).longValue());
                        orderSKU.setNum(basketItem.getCount());
                        orderSKU.setPrice(bPrice.longValue());
                        orderSKUs.add(orderSKU);
                    }
                    order.setSkuList(orderSKUs);
                    Gson gson = new Gson();
                    Type t = new TypeToken<Order>() {
                    }.getType();
                    String result = gson.toJson(order, t);
                    Log.d("组装请求参数", result);
                    byte[] bResult = Base64.encode(result.getBytes(), Base64.DEFAULT);
                    AbRequestParams params = new AbRequestParams();
                    params.put("orderData", new String(bResult));

                    AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
                    httpUtil.post(SXC_API.POST_DO_CREATE_ORDER, params, new AbStringHttpResponseListener() {
                        @Override
                        public void onSuccess(int i, String orderNo) {
                            Log.d("in do create order", orderNo);

                            //Toast.makeText(getApplicationContext(), orderNo, Toast.LENGTH_SHORT).show();

                            if (orderNo.startsWith("fail")) return;
                            //移除菜篮子中数据
                            BasketCacheManager.updateBasket(new ArrayList<BasketItem>(), getApplicationContext());

                            finish();
                            Intent intent = new Intent(DoOrderActivity.this, PayOrderActivity.class);
                            intent.putExtra("page", "立即支付");
                            intent.putExtra("userId", 162);
                            intent.putExtra("orderNo", orderNo);
                            intent.putExtra("money", String.valueOf(order.getDiscountedFee()));
                            startActivity(intent);
                        }

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onFailure(int i, String s, Throwable throwable) {

                            Log.e("get order error :",s);
                            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (Exception e) {
                    Log.e(this.getClass().getName(), "组装订单信息失败");
                }
            }
        });
    }
}
