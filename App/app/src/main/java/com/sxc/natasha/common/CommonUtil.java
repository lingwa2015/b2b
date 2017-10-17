package com.sxc.natasha.common;

import android.app.Activity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by bobo on 15/3/14.
 */
public class CommonUtil {

    public static String getPhoneNum(Activity context){
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getLine1Number();
    }

    public static void fixListViewHieght(ListView lv){
        if(lv.getAdapter() == null) return ;
        int totalHeight = 0;
        for(int i = 0; i < lv.getCount() ; i++){
            View lvItem = lv.getAdapter().getView(i,null,lv);
            try{
                lvItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                lvItem.measure(0,0); //TODO 点击我的订单就异常退出,先注释
            }catch(Exception e){
                Log.e("","",e);
            }

            totalHeight += lvItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams lp =  lv.getLayoutParams();
        lp.height = totalHeight + (lv.getDividerHeight() * (lv.getAdapter().getCount() - 1));
        lv.setLayoutParams(lp);
    }
}
