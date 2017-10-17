package com.sxc.natasha.task;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo on 15/3/25.
 */
public class BannerUpdateV2HomeFragment extends  BannerUpdateV2Task {

    String storeHouseUrl;
    public BannerUpdateV2HomeFragment(Activity activity, Handler handler, String bannerImgUrl,String storeHouseUrl) {
        super(activity, handler, bannerImgUrl);
        this.storeHouseUrl = storeHouseUrl;
    }


    @Override
    protected Void doInBackground(Void... params) {
        super.doInBackground(params);
        HttpClient hc = new DefaultHttpClient();
        HttpGet get = new HttpGet(storeHouseUrl);
        Log.i("", storeHouseUrl);
        try{
            HttpResponse hr = hc.execute(get);
            String apiResult = EntityUtils.toString(hr.getEntity());
            Gson gson = new Gson();
            Type t = new TypeToken<List<Map<String,String>>>(){}.getType();
            List<Map<String,String>> storeHouseList = gson.fromJson(apiResult,t);
            Message msg = new Message();
            msg.what =110;
            msg.obj = storeHouseList;
            handler.sendMessage(msg);

        }catch(Exception e){
            Log.e("but","",e);
        }

        return null;
    }
}
