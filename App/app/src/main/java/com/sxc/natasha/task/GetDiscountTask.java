package com.sxc.natasha.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ab.util.AbToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.SXC_API;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by bobo on 15/3/27.
 */
public class GetDiscountTask extends AsyncTask<Void,Integer,Void> {

    Activity activity;
    Handler handler;
    Integer skuTotalNum;
    Long orderTotalFee;
    public GetDiscountTask(Activity activity,Handler handler,Integer skuTotalNum,Long orderTotalFee){
        this.activity = activity;
        this.handler = handler;
        this.skuTotalNum = skuTotalNum;
        this.orderTotalFee = orderTotalFee;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String requestUrl = SXC_API.GET_DISCOUNTED_FEE.replace("@num",skuTotalNum+"").replace("@fee",orderTotalFee +"");
        HttpClient hc = new DefaultHttpClient();
        HttpGet get = new HttpGet(requestUrl);
        try {
            HttpResponse hr = hc.execute(get);
            String apiResult = EntityUtils.toString(hr.getEntity());
            Gson gson = new Gson();
            Type t = new TypeToken<Map<String,String>>(){}.getType();
            Map<String,String> result = gson.fromJson(apiResult,t);
            if("0".equals(result.get("status"))){
                Long discountVal = Long.parseLong(result.get("discountedFee"));
                Message msg = new Message();
                msg.what = 100;
                msg.obj = discountVal;
                handler.sendMessage(msg);
            }

        }catch (Exception e){
            Log.e("", "", e);
            AbToastUtil.showToast(activity, "获取优惠信息失败");
        }
        return null;
    }
}
