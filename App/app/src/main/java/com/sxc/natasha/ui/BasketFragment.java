package com.sxc.natasha.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.fragment.AbFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.image.AbImageLoader;
import com.ab.util.AbFileUtil;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.BasketCacheManager;
import com.sxc.natasha.common.CacheManager;
import com.sxc.natasha.common.CollectionUtil;
import com.sxc.natasha.common.Constant;
import com.sxc.natasha.common.DateUtil;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.BasketItem;
import com.sxc.natasha.domain.ItemModel;
import com.sxc.natasha.domain.ItemSPU;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author longpo
 */
public class BasketFragment extends AbFragment {

    private View view = null;
    private BaseAdapter adapter = null;
    private HashMap<String, BasketItem> data = null;
    private List<String> dataIds = null;
    private List<String> sellOutDataIds = null;
    private List<String> priceChangeDataIds = null;
    private ListView listView = null;
    private TextView attationTag = null;
    private TextView endTimeTag = null;
    private TextView totalItem = null;
    private TextView totalFee = null;
    private Button toOrderBtn = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(BasketFragment.class.getName(), "onResume");
        data.clear();
        dataIds.clear();
        sellOutDataIds.clear();
        priceChangeDataIds.clear();
        getData();
        changeTotalFeeAndCount();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.basket_fragment, null);
        endTimeTag = (TextView) view.findViewById(R.id.end_time_tag);
        totalFee = (TextView) view.findViewById(R.id.total_fee);
        totalItem = (TextView) view.findViewById(R.id.total_item);
        final Map<String, Integer> diffMap = DateUtil.getDifferentHoursAndMinutes(new Date(), Constant.CLOSE_TIME);

        if (diffMap.get("diffHours") <= 0 && diffMap.get("diffMinutes") <= 0) {
            endTimeTag.setText("今天结算已经完毕！");
        } else {
            endTimeTag.setText("距离今天最后结算时间还剩" + diffMap.get("diffHours") + "小时" + diffMap.get("diffMinutes") + "分");
        }

        listView = (ListView) view.findViewById(R.id.listView);
        data = new HashMap<String, BasketItem>();
        dataIds = new ArrayList<String>();
        sellOutDataIds = new ArrayList<String>();
        priceChangeDataIds = new ArrayList<String>();
        attationTag = new TextView(getActivity());
        toOrderBtn = (Button) view.findViewById(R.id.order_btn);
        attationTag.setGravity(Gravity.CENTER);
        attationTag.setTextColor(getResources().getColor(R.color.transparent_bg));
        getData();
        toOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (diffMap.get("diffHours") <= 0 && diffMap.get("diffMinutes") <= 0) {
                    AbToastUtil.showToast(getActivity().getApplicationContext(), "20:00 以后不再接受订单,请您明日赶早！");
                } else {
                    if (CollectionUtil.isEmpty(dataIds)) {
                        AbToastUtil.showToast(getActivity().getApplicationContext(), "菜篮子为空");
                        return;
                    }
                    saveBasket();
                    startActivity(new Intent(getActivity(), DoOrderActivity.class));
                }



                /*if (diffMap.get("diffHours") <= 0 && diffMap.get("diffMinutes") <= 0) {
                    AbToastUtil.showToast(getActivity().getApplicationContext(), "20:00 以后不再接受订单,请您明日赶早！");
                }else{
                                    }*/

            }
        });
        adapter = new BasketAdapter();
        listView.setAdapter(adapter);
        return view;
    }


    private void changeAttantion(int priceChangeNum, int sellOutNum) {
        listView.removeHeaderView(attationTag);
        if (priceChangeNum == 0 && sellOutNum == 0) {
            return;
        }
        String msg = "过去添加的";
        if (priceChangeNum != 0) {
            msg += priceChangeNum + "个商品价格已变动";
        }
        if (priceChangeNum != 0 && sellOutNum != 0) {
            msg += ",";
        }

        if (sellOutNum != 0) {
            msg += sellOutNum + "个商品已下架";
        }
        attationTag.setText(msg);
        listView.addHeaderView(attationTag);
    }

    private void changeTotalFeeAndCount() {
        float totalFeeNum = 0;
        int totalNum = dataIds.size() - sellOutDataIds.size();

        for (String id : dataIds) {
            if (sellOutDataIds.contains(id)) {
                continue;
            }
            BasketItem record = data.get(id);
            float price = Float.parseFloat(String.valueOf(record.getPrice()));
            int count = Integer.parseInt(String.valueOf(record.getCount()));
            totalFeeNum = totalFeeNum + (price * count);
        }
        totalFeeNum = Float.parseFloat(NumberTool.toYuanNumber((long) totalFeeNum));
        HomeFragment.totalFeeNum = totalFeeNum;
        HomeFragment.totalNum = totalNum;
        totalFee.setText(String.valueOf(totalFeeNum));
        totalItem.setText(String.valueOf(totalNum));
    }


    private void getData() {

        List<BasketItem> basketItems = BasketCacheManager.getBasket(getActivity().getApplicationContext());
        for (BasketItem basketItem : basketItems) {
            dataIds.add(String.valueOf(basketItem.getId()));
            data.put(String.valueOf(basketItem.getId()), basketItem);
        }

        if (!CollectionUtil.isEmpty(dataIds)) {

            AbHttpUtil httpUtil = AbHttpUtil.getInstance(getActivity());
            String url = SXC_API.GET_ITEM_BY_IDS + TextUtils.join("@", dataIds);
            httpUtil.get(url, new AbStringHttpResponseListener() {
                @Override
                public void onSuccess(int i, String s) {
                    ArrayList<ItemModel> itemModels = new Gson().fromJson(s, new TypeToken<ArrayList<ItemModel>>() {
                    }.getType());
                    HashMap<String, String> spuMap = new HashMap<String, String>(itemModels.size());
                    for (ItemModel itemModel : itemModels) {
                        BasketItem basketItem = data.get(String.valueOf(itemModel.getId()));
                        basketItem.setLimitPer(itemModel.getLimitPer());
                        basketItem.setSalStock(itemModel.getSalStock());
                        spuMap.put(String.valueOf(itemModel.getId()), String.valueOf(itemModel.getItemPrice()));
                    }

                    for (String id : data.keySet()) {
                        BasketItem record = data.get(id);
                        String price = spuMap.get(id);
                        if (price == null) {
                            sellOutDataIds.add(id);
                        } else if (Float.parseFloat(price) != record.getPrice()) {
                            priceChangeDataIds.add(id);
                            record.setPriceOld(record.getPrice());
                            record.setPrice(Float.parseFloat(price));
                        }
                    }
                    changeAttantion(priceChangeDataIds.size(), sellOutDataIds.size());
                }

                // 失败，调用
                @Override
                public void onFailure(int statusCode, String content,
                                      Throwable error) {
                    AbToastUtil.showToast(getActivity().getApplicationContext(), error.getMessage());
                }

                // 开始执行前
                @Override
                public void onStart() {
                }


                // 完成后调用，失败，成功
                @Override
                public void onFinish() {
                    changeTotalFeeAndCount();
                    adapter.notifyDataSetChanged();
                }
            });

        }
    }

    class BasketAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, final ViewGroup parent) {
            View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.basket_item, null);
            //商品图片
            ImageView item_image = (ImageView) view.findViewById(R.id.item_image);
            //商品名称
            TextView item_name = (TextView) view.findViewById(R.id.item_name);
            //商品价格
            TextView item_price = (TextView) view.findViewById(R.id.item_price);
            //老的商品价格
            TextView item_price_old = (TextView) view.findViewById(R.id.item_price_old);
            // 价格变动图标
            ImageView show_price_explain_img = (ImageView) view.findViewById(R.id.show_price_explain_img);
            //减
            ImageButton reduce_number = (ImageButton) view.findViewById(R.id.reduce_number);
            //加
            ImageButton add_number = (ImageButton) view.findViewById(R.id.add_number);
            //商品数量
            final TextView product_number = (TextView) view.findViewById(R.id.product_number);
            //售罄
            RelativeLayout sellout_layout = (RelativeLayout) view.findViewById(R.id.sellout_layout);

            final BasketItem record = data.get(dataIds.get(position));
            if (record.getImage() != null) {
                AbImageLoader mAbImageLoader = AbImageLoader.newInstance(getActivity().getApplicationContext());
                mAbImageLoader.setLoadingImage(R.drawable.image_loading);
                mAbImageLoader.setErrorImage(R.drawable.image_error);
                mAbImageLoader.setEmptyImage(R.drawable.image_empty);

                mAbImageLoader.setMaxWidth(80);
                mAbImageLoader.setMaxHeight(80);

                mAbImageLoader.display(item_image, record.getImage());
            }
            //
            item_name.setText(record.getTitle());
            item_price.setText("￥" + NumberTool.toYuanNumber((long) record.getPrice()));
            product_number.setText(""+record.getCount());

            reduce_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int num = Integer.parseInt(product_number.getText().toString());
                    if (num > record.getLimitPer()) {
                        Toast.makeText(getActivity(), "本商品限购" + record.getLimitPer(), Toast.LENGTH_SHORT).show();
                        num = record.getLimitPer();
                        product_number.setText("" + num);
                    }

                    if (num > record.getSalStock()) {
                        Toast.makeText(getActivity(), "本商品当前库存" + record.getSalStock(), Toast.LENGTH_SHORT).show();
                        num = record.getSalStock();
                        product_number.setText("" + num);
                    }
                    num --;
                    if (num == 0) {
                        if (dataIds.contains(String.valueOf(record.getId()))) {
                            dataIds.remove(String.valueOf(record.getId()));
                            data.remove(String.valueOf(record.getId()));
                            priceChangeDataIds.remove(String.valueOf(record.getId()));
                            sellOutDataIds.remove(String.valueOf(record.getId()));
                            changeAttantion(priceChangeDataIds.size(), sellOutDataIds.size());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        BasketItem record1 = data.get(String.valueOf(record.getId()));
                        record1.setCount(num);
                        product_number.setText("" + num);
                    }
                    changeTotalFeeAndCount();
                }
            });

            add_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int num = Integer.parseInt(product_number.getText().toString());
                    if (num > record.getLimitPer()) {
                        Toast.makeText(getActivity(), "本商品限购" + record.getLimitPer(), Toast.LENGTH_SHORT).show();
                        num = record.getLimitPer();
                        product_number.setText("" + num);
                    }

                    if (num > record.getSalStock()) {
                        Toast.makeText(getActivity(), "本商品当前库存" + record.getSalStock(), Toast.LENGTH_SHORT).show();
                        num = record.getSalStock();
                        product_number.setText("" + num);
                    }
                    num ++;
                    if (num == 0) {
                        if (dataIds.contains(String.valueOf(record.getId()))) {
                            dataIds.remove(String.valueOf(record.getId()));
                            data.remove(String.valueOf(record.getId()));
                            priceChangeDataIds.remove(String.valueOf(record.getId()));
                            sellOutDataIds.remove(String.valueOf(record.getId()));
                            changeAttantion(priceChangeDataIds.size(), sellOutDataIds.size());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        BasketItem record1 = data.get(String.valueOf(record.getId()));
                        record1.setCount(num);
                        product_number.setText("" + num);
                    }
                    changeTotalFeeAndCount();
                }
            });
            //
            if (record.getPriceOld() != 0) {
                item_price_old.setText("￥" + NumberTool.toYuanNumber((long) record.getPriceOld()));
                item_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                item_price_old.setVisibility(View.VISIBLE);
                //降价
                if (record.getPriceOld() > record.getPrice()) {

                    show_price_explain_img.setImageResource(R.drawable.shoppingcart_05);
                } else { //涨价
                    show_price_explain_img.setImageResource(R.drawable.shoppingcart_07);
                }
                show_price_explain_img.setVisibility(View.VISIBLE);
            }

            if (sellOutDataIds.contains(String.valueOf(record.getId()))) {
                sellout_layout.setVisibility(View.VISIBLE);
                view.setEnabled(false);
            }
            return view;
        }
    }

    private void saveBasket() {
        for (String id : sellOutDataIds) {
            data.remove(id);
        }
        List<BasketItem> list = new ArrayList<BasketItem>();
        for (BasketItem basketItem : data.values()) {
            basketItem.setPriceOld(0);
            list.add(basketItem);
        }
        BasketCacheManager.updateBasket(list, getActivity().getApplicationContext());
    }

    @Override
    public void onStop() {
        saveBasket();
        super.onStop();
    }
}
