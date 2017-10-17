package com.sxc.natasha.ui;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.CommonUtil;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.OrderStatusEnum;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.Order;
import com.sxc.natasha.domain.OrderSKU;
import com.sxc.natasha.ui.R;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailActivity extends Activity {


    private Order order;

    private Activity mySelf;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        String orderNo = getIntent().getStringExtra("orderNo");

        try {
            setContentView(R.layout.activity_order_detail);

            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
            setTitle("订单详情");
            mySelf = this;
            getOrder(orderNo);
        }catch (Throwable e){
            Log.e("error",e.getMessage());
        }




    }


    private Order getOrder(String orderNo){
        if(orderNo == null || orderNo.length() == 0){
            return null;
        }
        AbHttpUtil httpUtil = AbHttpUtil.getInstance(getApplicationContext());
        httpUtil.get(SXC_API.GET_ORDER_DETAIL+orderNo,new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                Log.d("test",s);

                Type t = new TypeToken<Order>(){}.getType();
                Gson gson = new Gson();

                order = gson.fromJson(s,t);
                TextView orderNo = (TextView)mySelf.findViewById(R.id.order_detail_order_no);
                orderNo.setText(order.getOrderNo());
                TextView orderState = (TextView)mySelf.findViewById(R.id.order_detail_order_state);
                orderState.setText(OrderStatusEnum.parseId(order.getOrderStatus()).getValue());
                TextView orderCreate = (TextView)mySelf.findViewById(R.id.order_detail_order_create_time);
                orderCreate.setText(sdf.format(order.getCreateTime()));

                TextView orderPayTime = (TextView)mySelf.findViewById(R.id.order_detail_order_pay_time);
                if(order.getPayTime() != null){
                    orderPayTime.setText(sdf.format(order.getPayTime()));
                }

                TextView orderPickTime = (TextView)mySelf.findViewById(R.id.order_detail_order_pick_time);
                orderPickTime.setText(sdf.format(order.getPicktime()));
                TextView orderBuyerName = (TextView)mySelf.findViewById(R.id.order_detail_buyer_name);
                orderBuyerName.setText(order.getBuyerName());
                TextView orderBuyerPhone = (TextView)mySelf.findViewById(R.id.order_detail_buyer_mphone);
                orderBuyerPhone.setText(order.getBuyerMobilePhone());
                TextView orderpickHouse = (TextView)mySelf.findViewById(R.id.order_detail_pick_house);
                orderpickHouse.setText(order.getPickStoreHouseName());
                TextView orderpickAddress = (TextView)mySelf.findViewById(R.id.order_detail_pick_address);
                orderpickAddress.setText(order.getPickStoreHouseAddress());
                TextView ordermanagerName = (TextView)mySelf.findViewById(R.id.order_detail_manager_name);
                ordermanagerName.setText(order.getPickStoreHouseManagerName());
                TextView orderManagerPhoneNum = (TextView)mySelf.findViewById(R.id.order_detail_manager_phone_num);
                orderManagerPhoneNum.setText(order.getPickStoreHouseManagerPhone());
                TextView orderskuTotalFee = (TextView)mySelf.findViewById(R.id.order_detail_sku_total_fee);

                ListView lv = (ListView)mySelf.findViewById(R.id.order_detail_sku_list);

                List<HashMap<String,Object>> skuListData = new ArrayList<HashMap<String, Object>>();

                Long skuTotalFee = 0l;
                for(OrderSKU sku : order.getSkuList()){
                    skuTotalFee += sku.getFee();
                    HashMap<String,Object> rowData = new HashMap<String, Object>();
                    rowData.put("skuName",sku.getSkuName());
                    rowData.put("skuTotalFee",NumberTool.toYuanNumber(sku.getFee()));
                    rowData.put("skuNum",sku.getNum() +"件");
                    skuListData.add(rowData);
                }

                SimpleAdapter sadapter = new SimpleAdapter(getApplicationContext(),skuListData,R.layout.order_detail_item,
                       new String[]{"skuName","skuTotalFee","skuNum"},new int[]{R.id.order_detail_item_name,
                                                                                R.id.order_detail_item_fee,
                                                                                R.id.order_detail_item_sku_num} );
                lv.setAdapter(sadapter);

                CommonUtil.fixListViewHieght(lv);
                orderskuTotalFee.setText("￥" + NumberTool.toYuanNumber(skuTotalFee));

                TextView orderDiscount = (TextView)mySelf.findViewById(R.id.order_detail_discount);
                TextView orderOrderTotalFee = (TextView)mySelf.findViewById(R.id.order_detail_order_total_fee);

                if(order.getDiscountedFee() != null && order.getDiscountedFee() != 0l){
                    orderDiscount.setText(NumberTool.toYuanNumber(order.getTotalFee() - order.getDiscountedFee()));
                    orderOrderTotalFee.setText("￥" + NumberTool.toYuanNumber(order.getDiscountedFee()));

                }else{
                    orderDiscount.setText("0.0");
                    orderOrderTotalFee.setText("￥" + NumberTool.toYuanNumber(skuTotalFee));

                }





                ImageView statusIv =(ImageView)findViewById(R.id.order_detail_status_img);

                setOrderStatusImg(statusIv,order);

                TextView headerStateTv = (TextView) findViewById(R.id.order_detai_order_state_header);

                Button chargeBtn = (Button)findViewById(R.id.charge_btn);

                if(OrderStatusEnum.UNPAY.getId() ==  order.getOrderStatus().intValue()){
                    chargeBtn.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplication(), PayOrderActivity.class);
                            intent.putExtra("page", "立即支付");
                            intent.putExtra("userId", order.getBuyerId());
                            intent.putExtra("orderNo", order.getOrderNo());

                            if(order.getDiscountedFee() != null && order.getDiscountedFee() != 0l){
                                intent.putExtra("money", order.getDiscountedFee()+"");

                            }else{
                                intent.putExtra("money", order.getTotalFee()+"");

                            }

                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplication().startActivity(intent);
                        }
                    });

                    Drawable da = getResources().getDrawable(R.drawable.waitpay);
                    if(da != null){
                        headerStateTv.setText("");
                        headerStateTv.setBackground(da);
                    }
                }else{
                    //如果不是未付款 状态 设置按钮不可用
                    chargeBtn.setVisibility(View.GONE);

                    headerStateTv.setText(OrderStatusEnum.parseId(order.getOrderStatus()).getValue());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                    headerStateTv.setLayoutParams(lp);
                }

                /*
                order_detail_order_no












                 */

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {

            }
        });
        return null;

    }




    public void setOrderStatusImg(ImageView iv,Order order){
        if(OrderStatusEnum.UNPAY.getId() == order.getOrderStatus().intValue() ||
                OrderStatusEnum.WAITPICK.getId() == order.getOrderStatus().intValue()){
            iv.setImageResource(R.drawable.order_status_1);

        }else if(OrderStatusEnum.PICKING.getId() == order.getOrderStatus().intValue() ||
                OrderStatusEnum.UNSHIP.getId() == order.getOrderStatus().intValue()){
            iv.setImageResource(R.drawable.order_status_3);

        }else if(OrderStatusEnum.SHIPPING.getId() == order.getOrderStatus().intValue()){
            iv.setImageResource(R.drawable.order_status_5);

        }else if(order.getOrderStatus().intValue() >= OrderStatusEnum.UNDELIVER.getId()){
            iv.setImageResource(R.drawable.order_status_6);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_detail, menu);
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
