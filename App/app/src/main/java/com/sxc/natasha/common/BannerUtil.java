package com.sxc.natasha.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bobo on 15/3/24.
 */
public class BannerUtil {

    public static Bitmap getBannerImg(String fileName, Context context) {
        Bitmap result = null;
        String appPathName = context.getFilesDir().getAbsolutePath();
        String imgFileName = appPathName + "/img/banner/" + fileName;

        try {
            FileInputStream fis = new FileInputStream(imgFileName);
            result = BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            Log.e("ddd", e.toString());
        }


        return result;
    }

    public static Bitmap getBannerImg(String url) {
        Bitmap result = null;
        try {
            URL fileUrl = new URL(url);
            Log.i("",url);

            HttpURLConnection conn = (HttpURLConnection)fileUrl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            InputStream is = conn.getInputStream();
            result = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.e("dd", e.toString());
        }
        return result;
    }

}