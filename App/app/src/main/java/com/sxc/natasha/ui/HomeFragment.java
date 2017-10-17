package com.sxc.natasha.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.ab.fragment.AbFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.image.AbImageLoader;
import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.sliding.AbSlidingPlayView;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.BannerUtil;
import com.sxc.natasha.common.CollectionUtil;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.ItemModel;
import com.sxc.natasha.domain.User;
import com.sxc.natasha.task.BannerUpdateV2HomeFragment;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends AbFragment implements AbPullToRefreshView.OnHeaderRefreshListener {
    private BaseAdapter adapter = null;
    private List<ItemModel> data = null;
    private List<String> tagPositions = null;
    private AbPullToRefreshView mAbPullToRefreshView = null;
    private ListView mListView = null;

    public AbSlidingPlayView mSlidingPlayView = null;
    private View helpView = null;
    private LayoutInflater mInflater = null;
    private View view = null;
    private TextView totalFee = null;
    private TextView totalItem = null;
    public static float totalFeeNum = 0;
    public static int totalNum = 0;
    private RelativeLayout toOrderLayout = null;
    private Button order_btn = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //组和个AbSlidingPlayView
        mSlidingPlayView = new AbSlidingPlayView(getActivity().getApplicationContext());
        mInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSlidingPlayView.setNavHorizontalGravity(Gravity.CENTER);
        //设置高度
        mSlidingPlayView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
        mSlidingPlayView.startPlay();

        helpView = mInflater.inflate(R.layout.home_help_layout, null);

        TextView use_help = (TextView) helpView.findViewById(R.id.use_help);
        use_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpCommonActivity.class);
                intent.putExtra("page", "help");
                getActivity().startActivity(intent);
            }
        });


        TextView serviceMsg = (TextView) helpView.findViewById(R.id.serviceMsg);
        serviceMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpCommonActivity.class);
                getActivity().startActivity(intent);
            }
        });




    }

    private void updateBannerImg(List<Bitmap> urlList){


        try{
            for(Bitmap imgByte : urlList){
                final View mPlayView = mInflater.inflate(R.layout.play_view_item, null);
                ImageView mPlayImage = (ImageView) mPlayView.findViewById(R.id.mPlayImage);
                TextView mPlayText = (TextView) mPlayView.findViewById(R.id.mPlayText);
                mPlayText.setVisibility(View.GONE);
                mPlayImage.setImageBitmap(imgByte);
                mSlidingPlayView.addView(mPlayView);
            }
        }catch (Exception e){
            Log.e("upm","",e);
        }

    }

    /**
     *
     * @param storeHouseList
     */
    private void updateServiceManager(List<Map<String,String>> storeHouseList){
        try{
            TextView recharge = (TextView) helpView.findViewById(R.id.recharge);

            User user = UserCache.getUser(getActivity());
            Map<String,String> tmpStoreHouse = null;
            for(Map<String,String> row : storeHouseList){
                String shId = row.get("id");

                if(!StringUtils.isEmpty(shId)){
                    if(shId.equals(user.getPickhoseId()+"")){
                        tmpStoreHouse = row;
                    }
                }

            }
            final Map<String,String> storeHouse = tmpStoreHouse;


            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(storeHouse == null){
                        AbToastUtil.showToast(getActivity(),"没有找到您的服务经理信息");
                        return;
                    }
                    View view = mInflater.inflate(R.layout.home_recharge_layout, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    TextView managerTv = (TextView)view.findViewById(R.id.home_recharge_manager);
                    TextView phoneTv = (TextView)view.findViewById(R.id.home_recharge_phone);

                    managerTv.setText(storeHouse.get("manager"));
                    phoneTv.setText(storeHouse.get("phone"));

                    builder.setView(view);
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("拨打", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+storeHouse.get("phone")));
                            getActivity().startActivity(intent);
                        }
                    });
                    builder.show();
                }
            });



        }catch (Exception e){
            Log.e("usm","",e);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.home_fragment, null);

            mAbPullToRefreshView = (AbPullToRefreshView) view.findViewById(R.id.mPullRefreshView);
            mListView = (ListView) view.findViewById(R.id.mListView);
            totalFee = (TextView) view.findViewById(R.id.total_fee);
            totalItem = (TextView) view.findViewById(R.id.total_item);
            toOrderLayout = (RelativeLayout)view.findViewById(R.id.to_order);
            order_btn = (Button) view.findViewById(R.id.order_btn);
            //设置监听器
            mAbPullToRefreshView.setOnHeaderRefreshListener(this);
            //关闭下拉刷新
            mAbPullToRefreshView.setLoadMoreEnable(false);

            //设置进度条的样式
            mAbPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));

            //使用自定义的Adapter
            data = new ArrayList<ItemModel>();
            tagPositions = new ArrayList<String>();
            adapter = new GroupAdapter();
            //setAdapter和addHeaderView顺序问题，为了兼容4.3以下的版本
            //mListView.setAdapter(adapter);
            mListView.addHeaderView(mSlidingPlayView);
            //解决冲突问题
            mSlidingPlayView.setParentListView(mListView);

            mListView.addHeaderView(helpView);
            mListView.setAdapter(adapter);
            getData("");
            //点击具体行到商品详情
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(tagPositions.contains(String.valueOf(id))){
                        return;
                    }
                    Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
                    intent.putExtra("itemId", data.get((int)id).getId());
                    //使用Bundle 传递Serializable对象
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("item", data.get((int)id));
                    intent.putExtras(mBundle);
                    getActivity().startActivity(intent);
                }
            });

            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    int flag = msg.what;

                    if(flag == 100){
                        List<Bitmap> urlList = (List<Bitmap>)msg.obj;
                        updateBannerImg(urlList);
                    }else if(flag == 110){
                        List<Map<String,String>> storeHouseList = (List<Map<String,String>>)msg.obj;
                        updateServiceManager(storeHouseList);
                    }


                }
            };



            try{
                BannerUpdateV2HomeFragment task = new BannerUpdateV2HomeFragment(this.getActivity(),handler,SXC_API.BANNER_UPDATE_URL,SXC_API.GET_ALL_STORE_HOUSE);
                task.execute();
            }catch (Exception e){
                Log.e("e",e.toString());
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        changeTotalFeeAndCount();
    }

    public void getData(final String processType) {
        AbHttpUtil httpUtil = AbHttpUtil.getInstance(getActivity());
        httpUtil.get(SXC_API.GET_ALL_ITEM, new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                Log.d("HomeFragment", s);
                HashMap<String, ArrayList<ItemModel>> map = new Gson().fromJson(s, new TypeToken<HashMap<String, ArrayList<ItemModel>>>(){}.getType());
                int position = 0;
                for (String key : map.keySet()) {
                    ArrayList<ItemModel> values =  map.get(key);
                    if(CollectionUtil.isEmpty(values)){
                        continue;
                    }
                    ItemModel itemModel = new ItemModel();
                    itemModel.setItemTitle(key);
                    tagPositions.add(String.valueOf(position++));
                    data.add(itemModel);
                    data.addAll(values);
                    position += values.size();
                }
                adapter.notifyDataSetChanged();
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
                //显示进度框
                Log.d("HomeFragmet", "开始请求");
            }


            // 完成后调用，失败，成功
            @Override
            public void onFinish() {
                //移除进度框
                Log.d("HomeFragmet", "请求结束");
                if ("refresh".equals(processType)) {
                    mAbPullToRefreshView.onHeaderRefreshFinish();
                }
            }
        });
    }



    @Override
    public void onHeaderRefresh(AbPullToRefreshView abPullToRefreshView) {
        data.clear();
        getData("refresh");
    }

    private class GroupAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ItemModel record = (ItemModel) getItem(position);
            if (record.getId() == 0) {
                view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.home_list_item_tag, null);
                TextView tag = (TextView) view.findViewById(R.id.list_tag);
                tag.setText(record.getItemTitle());
            } else {
                view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.home_list_item, null);
                ImageView img = (ImageView) view.findViewById(R.id.item_image);
                AbImageLoader mAbImageLoader = AbImageLoader.newInstance(getActivity().getApplicationContext());
                mAbImageLoader.setLoadingImage(R.drawable.image_loading);
                mAbImageLoader.setErrorImage(R.drawable.image_error);
                mAbImageLoader.setEmptyImage(R.drawable.image_empty);

                mAbImageLoader.setMaxWidth(80);
                mAbImageLoader.setMaxHeight(80);
                mAbImageLoader.display(img,record.getImg());

                TextView title = (TextView) view.findViewById(R.id.item_title);
                title.setText(record.getItemTitle());
                TextView price = (TextView) view.findViewById(R.id.item_price);

                Resources resource = (Resources) getActivity().getResources();
                ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.red);

                if(csl != null) price.setTextColor(csl);

                price.setText("￥" + NumberTool.toYuanNumber((long)record.getItemPrice()));
                TextView priceKG = (TextView) view.findViewById(R.id.item_price_kg);
                priceKG.setText(String.valueOf(NumberTool.toYuanNumber((long)record.getBjPrice()))+"/百斤");
                TextView marketPrice = (TextView) view.findViewById(R.id.market_price);
                marketPrice.setText("200-300");
            }
            return view;
        }
    }

    private void changeTotalFeeAndCount(){
        if(totalNum != 0){
            toOrderLayout.setVisibility(View.VISIBLE);
            totalFee.setText(String.valueOf(totalFeeNum));
            totalItem.setText(String.valueOf(totalNum));
            order_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).setOnClick(R.id.id_tab_basket);
                }
            });
        }else{
            toOrderLayout.setVisibility(View.GONE);
        }
    }


    public void getBannerImg(){
        HttpClient hc = new DefaultHttpClient();
        HttpGet getMethod = new HttpGet(SXC_API.BANNER_UPDATE_URL);

    }
}
