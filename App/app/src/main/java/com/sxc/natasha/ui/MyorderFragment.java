package com.sxc.natasha.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ab.fragment.AbFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.adapter.OrderListAdapter;


import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.CommonUtil;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.domain.Order;
import com.sxc.natasha.domain.User;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyorderFragment extends AbFragment
        implements AbPullToRefreshView.OnHeaderRefreshListener,AbPullToRefreshView.OnFooterLoadListener {


    private Activity mActivity;
    private OrderListAdapter adapter = null;
    private ListView listView = null;
    private View myself;
    List<Order> orderList = new ArrayList<Order>();
    private AbPullToRefreshView mAbPullToRefreshView = null;

    DecimalFormat decimalFormat = new DecimalFormat(".00");

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        if(savedInstanceState!= null && savedInstanceState.getSerializable("test") != null){
            ArrayList<HashMap<String, Object>> test = (ArrayList<HashMap<String, Object>>)savedInstanceState.getSerializable("test");
        }

        super.onCreate(savedInstanceState);

        //if(orderList == null){
          //  getData();
        //}
        //setRetainInstance(true);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(myself == null){
            myself = inflater.inflate(R.layout.myorder_fragment, null);
            listView = (ListView) myself.findViewById(R.id.listView);

            // String phoneNum = CommonUtil.getPhoneNum(mActivity);
          //  listView.setLoadMoreInterface(MyorderFragment.this);
            //listView.setReflashInterface(MyorderFragment.this);

            mAbPullToRefreshView = (AbPullToRefreshView)myself.findViewById(R.id.mPullRefreshView);
            //设置监听器
            mAbPullToRefreshView.setOnHeaderRefreshListener(this);
            mAbPullToRefreshView.setOnFooterLoadListener(this);

            //设置进度条的样式
            mAbPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));
            mAbPullToRefreshView.getFooterView().setFooterProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));

            adapter = new OrderListAdapter(orderList, R.layout.order_item,mActivity);
            listView.setAdapter(adapter);

            getData("");

        }

        return myself;
    }

    public void getData(final String processType){
        AbHttpUtil httpUtil = AbHttpUtil.getInstance(mActivity);
        //String phoneNum = CommonUtil.getPhoneNum(mActivity);

        User user = UserCache.getUser(getActivity());
        if(user == null) {
            AbToastUtil.showToast(getActivity(),"没有找到用户信息");
            return;
        }

        httpUtil.get(SXC_API.GET_USER_ORDER+user.getMobilePhone(),new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    Log.d("myorderFragment",s);
                    Type t = new TypeToken<List<Order>>(){}.getType();
                    Gson gson = new Gson();
                    orderList = gson.fromJson(s,t);
                    adapter.setDataList(orderList);
                    adapter.notifyDataSetChanged();

                }catch (Throwable e){
                    Log.e("error",e.getMessage());
                    AbToastUtil.showToast(getActivity().getApplicationContext(), e.getMessage());

                }



            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

                if("refresh".equals(processType))
                mAbPullToRefreshView.onHeaderRefreshFinish();


            }

            @Override
            public void onFailure(int i, String s, Throwable error) {
                AbToastUtil.showToast(getActivity().getApplicationContext(), error.getMessage());

            }
        });

    }



    @Override
    public void onDestroy() {

        super.onDestroy();
    }



    @Override
    public void onHeaderRefresh(AbPullToRefreshView abPullToRefreshView) {
        getData("refresh");
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView abPullToRefreshView) {
        mAbPullToRefreshView.onFooterLoadFinish();
    }
}
