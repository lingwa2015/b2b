package com.sxc.natasha.task;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.view.sliding.AbSlidingPlayView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.BannerUtil;
import com.sxc.natasha.common.CacheManager;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.ui.HomeFragment;
import com.sxc.natasha.ui.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo on 15/3/24.
 */
public class BannerUpdateV2Task extends AsyncTask<Void,Integer,Void> {

    private static String CACHE_KEY = "banner_img";


    Activity activity;
    Handler handler;
    String requestUrl;
    public BannerUpdateV2Task(Activity activity,Handler handler,String requestUrl){
        this.activity = activity;
        this.handler = handler;
        this.requestUrl = requestUrl;
    }


    private String parserFileName(String url){
        if(StringUtils.isEmpty(url)) return "";
        String[] tmp1 = url.split("\\?");
        if(tmp1.length == 2){
            String[] tmp2 = tmp1[1].split("=");
            if(tmp2.length == 2) return tmp2[1];
        }
        return "";
    }


    private void downLoadImg(String url){
        try {
            URL fileUrl = new URL(url);
            Log.i("",url);
            HttpURLConnection conn = (HttpURLConnection)fileUrl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int length = 0;

            String appDataPath = this.activity.getFilesDir().getAbsolutePath();


            String fileDirName =appDataPath +  "/img/banner/";

            File dir = new File(fileDirName);
            if(!dir.exists()){
                dir.mkdirs();
            }

            String fileName = fileDirName + parserFileName(url);

           // FileOutputStream fos = this.homeFragment.getActivity().openFileOutput(fileName,Context.MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(fileName);

            while((length = is.read(buffer)) > 0){
                fos.write(buffer);
            }
            fos.close();

        }catch (Exception e){
            Log.e("",e.toString());
        }
    }



    @Override
    protected Void doInBackground(Void... params) {
        HttpClient hc = new DefaultHttpClient();
        HttpGet get = new HttpGet(requestUrl);
        Log.i("",requestUrl);

        try {
            HttpResponse hr = hc.execute(get);

            String apiResult = EntityUtils.toString(hr.getEntity());
            Gson gson = new Gson();
            Type t = new TypeToken<List<Map<String,String>>>(){}.getType();
            List<Map<String,String>> bannerList = gson.fromJson(apiResult,t);

            /*
            start procee banner img
             */

            List<Bitmap> urlList = new ArrayList<Bitmap>();
            for(Map<String,String> row : bannerList){
                String url = row.get("imgUrl");
                urlList.add(BannerUtil.getBannerImg(url));
            }
            Message msg = new Message();
            msg.obj = urlList;
            msg.what = 100;

            this.handler.sendMessage(msg);
            //Log.e("dd",apiResult);
        } catch (Exception e) {
           Log.e("ee",e.toString());
        }

        return null;
    }
}
