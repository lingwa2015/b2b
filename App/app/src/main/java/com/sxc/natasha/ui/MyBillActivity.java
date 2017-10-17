package com.sxc.natasha.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ab.http.AbHttpUtil;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.adapter.AccountDetailListAdapter;
import com.sxc.natasha.common.NumberTool;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.domain.AccountDetail;
import com.sxc.natasha.domain.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longpo on 2015/3/23.
 */
public class MyBillActivity extends Activity {
    private LayoutInflater mInflater = null;

    private MyBillActivity myselfActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.my_bill_list_layout);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
        this.mInflater = (LayoutInflater) this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView title = (TextView) findViewById(R.id.title);
        TextView title_back = (TextView) findViewById(R.id.title_back);
        title_back.setVisibility(View.VISIBLE);
        title.setText("我的账单");
        final int userId = getIntent().getIntExtra("userId", -1);

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final ListView listView = (ListView)findViewById(R.id.bill_listView);
        //查询账单
        final AbHttpUtil httpUtil = AbHttpUtil.getInstance(this);
        httpUtil.get(SXC_API.GET_ACCOUNT_DETAIL + userId,new AbStringHttpResponseListener() {
            @Override
            public void onSuccess(int i, String s) {
                try {
                    Log.d("MyBillActivity", s);
                    Gson gson = new Gson();
                    Type detailTypeToken = new TypeToken<List<AccountDetail>>(){}.getType();
                    List<AccountDetail> details = gson.fromJson(s, detailTypeToken);
                    Log.d("MyBillActivity", ""+details.size());
                    listView.setAdapter(new AccountDetailListAdapter(details, R.layout.bill_item_layout, myselfActivity()));
                } catch (Throwable e){
                    Log.e("error",e.getMessage());
                    AbToastUtil.showToast(getApplicationContext(), e.getMessage());
                }
            }
            @Override
            public void onStart() {
            }
            @Override
            public void onFinish() {
                Log.d("MyBillActivity", "onFinish");
            }
            @Override
            public void onFailure(int i, String s, Throwable error) {
                AbToastUtil.showToast(getApplicationContext(), error.getMessage());

            }
        });
    }

}
