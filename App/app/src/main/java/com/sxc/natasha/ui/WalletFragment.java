package com.sxc.natasha.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.fragment.AbFragment;
import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.sxc.natasha.cache.UserCache;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.domain.User;

import org.json.JSONObject;

public class WalletFragment extends AbFragment
        implements AbPullToRefreshView.OnHeaderRefreshListener,AbPullToRefreshView.OnFooterLoadListener {
    final AbHttpUtil httpUtil = AbHttpUtil.getInstance(getActivity());
    private View view;
    private AbPullToRefreshView mAbPullToRefreshView = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            this.view = inflater.inflate(R.layout.wallet_fragment, null);

            mAbPullToRefreshView = (AbPullToRefreshView)view.findViewById(R.id.mPullRefreshView);
            //设置监听器
            mAbPullToRefreshView.setOnHeaderRefreshListener(this);
            mAbPullToRefreshView.setOnFooterLoadListener(this);

            //设置进度条的样式
            mAbPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));
            mAbPullToRefreshView.getFooterView().setFooterProgressBarDrawable(
                    this.getResources().getDrawable(R.drawable.progress_circular));

            final User user = UserCache.getUser(getActivity());

            if (user == null) {
                AbToastUtil.showToast(getActivity(),"没有找到用户信息");
            } else {
                loadUserAccount("");
                ((RelativeLayout) view.findViewById(R.id.my_bill_btn)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), MyBillActivity.class);
                        intent.putExtra("userId", user.getUserId());
                        startActivity(intent);
                    }
                });
            }
            RelativeLayout callPhonePanel = (RelativeLayout)view.findViewById(R.id.account_callPhone);
            callPhonePanel.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:15874980315"));
                    getActivity().startActivity(intent);
                }
            });
        }

        return view;
    }

    private void loadUserAccount(final String processType) {
        final User user = UserCache.getUser(getActivity());


        httpUtil.get(SXC_API.GET_USER_ACCOUNT +"?userId=" + user.getId(),new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    JSONObject obj = new JSONObject(s);
                    Long balanceFen = obj.getLong("balance");
                    String balance = "0";
                    if (balanceFen != 0) {
                        balance = NumberTool.toYuanNumber(balanceFen);
                    }
                    TextView showBalance = (TextView)view.findViewById(R.id.wallet_balance);
                    showBalance.setText("￥：" + balance);
                } catch (Throwable e){
                    Log.e("error",e.getMessage());
                    AbToastUtil.showToast(getActivity().getApplicationContext(), e.getMessage());
                }
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                Log.d("WalletFragment","onFinish");
                if("ref".equals(processType)){
                    mAbPullToRefreshView.onHeaderRefreshFinish();
                }else if ("load".equals(processType)){
                    mAbPullToRefreshView.onFooterLoadFinish();
                }

            }

            @Override
            public void onFailure(int i, String s, Throwable error) {
                AbToastUtil.showToast(getActivity().getApplicationContext(), error.getMessage());

            }
        });

    }

    @Override
    public void onFooterLoad(AbPullToRefreshView abPullToRefreshView) {
        loadUserAccount("load");
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView abPullToRefreshView) {
        loadUserAccount("ref");
    }
}
