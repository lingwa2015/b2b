package com.sxc.natasha.common;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.ContentHandler;

/**
 * Created by bobo on 15/3/18.
 */
public class CacheManager {
    /**
     * 菜篮子部分文件
     */
    public static final String CART_SHOPPING_KEY = "sxc_cart";

    /**
     * 主页商品列表
     */
    public static final String HOME_ITEM_LIST_KEY = "sxc_home";

    public static boolean write(String key, String cache, Context context) {
        /*String appFilePath = context.getFilesDir().getAbsolutePath();
        String filePath =appFilePath + File.separator + key;
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }*/

        try {
            FileOutputStream fos = context.openFileOutput(key, context.MODE_PRIVATE);
            byte[] cacheBytee = cache.getBytes("utf-8");

            fos.write(cacheBytee);
            fos.close();
        } catch (Exception e) {

            Log.e("error", e.getMessage());
            return false;
        }

        return true;
    }

    public static String read(String key, Context context) {
        try {
            FileInputStream fio = context.openFileInput(key);
            byte[] r = new byte[fio.available()];
            fio.read(r);
            String result = new String(r, "utf-8");
            return result;
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return "";
    }
}
