package com.sxc.natasha.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.CacheManager;
import com.sxc.natasha.common.SXC_API;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.ui.HomeFragment;

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
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bobo on 15/3/24.
 */
public class BannerUpdateTask extends AsyncTask<Void,Integer,Void> {

    private static String CACHE_KEY = "banner_img";


    private HomeFragment homeFragment;
    public BannerUpdateTask(HomeFragment homeFragment){
        this.homeFragment = homeFragment;
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
            HttpURLConnection conn = (HttpURLConnection)fileUrl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int length = 0;

            String appDataPath = this.homeFragment.getActivity().getFilesDir().getAbsolutePath();


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
        HttpGet get = new HttpGet(SXC_API.BANNER_UPDATE_URL);
        try {
            HttpResponse hr = hc.execute(get);

            String apiResult = EntityUtils.toString(hr.getEntity());
            Gson gson = new Gson();
            Type t = new TypeToken<List<Map<String,String>>>(){}.getType();
            List<Map<String,String>> bannerList = gson.fromJson(apiResult,t);

            String oldCache = CacheManager.read(CACHE_KEY,homeFragment.getActivity());

            if(StringUtils.isEmpty(oldCache)){
                CacheManager.write(CACHE_KEY,apiResult,homeFragment.getActivity());
                for(Map<String,String> rowData : bannerList){
                    downLoadImg(rowData.get("imgUrl"));
                }
                return null;
            }

            List<Map<String,String>> oldBannerList = gson.fromJson(oldCache, t);


            if(oldBannerList.size() != bannerList.size()){
                CacheManager.write(CACHE_KEY,apiResult,homeFragment.getActivity());
                for(Map<String,String> rowData : bannerList){
                    downLoadImg(rowData.get("imgUrl"));
                }
                return null;
            }

            for(int i =0;i< bannerList.size() ; i++){
                Map<String,String> newRow = bannerList.get(i);
                Map<String,String> oldRow = oldBannerList.get(i);

                if(newRow.get("md5") == null) continue;


                if(!newRow.get("md5").equals(oldRow.get("md5"))){
                    oldRow.put("md5",newRow.get("md5"));
                    oldRow.put("imgUrl",newRow.get("imgUrl"));

                    downLoadImg(oldRow.get("imgUrl"));
                }
            }

            CacheManager.write(CACHE_KEY,gson.toJson(oldBannerList,t),homeFragment.getActivity());

            //Log.e("dd",apiResult);
        } catch (IOException e) {
           Log.e("ee",e.toString());
        }

        return null;
    }
}
