package com.sxc.natasha.common;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.domain.BasketItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longpo on 15/3/19.
 */
public class BasketCacheManager {


    public static boolean addBasketRecord(BasketItem basketItem, Context context) {
        boolean flag = false;
        try {
            List<BasketItem> basketItems = getBasket(context);
            boolean isExist = false;
            for(BasketItem item : basketItems){
                if(item.getId() == basketItem.getId()){
                    item.setCount(item.getCount()+basketItem.getCount());
                    isExist = true;
                }
            }
            if(!isExist){
                basketItems.add(basketItem);
            }
            updateBasket(basketItems, context);
        } catch (Exception e) {
            Log.e(BasketCacheManager.class.getName(), "添加菜篮子缓存出错", e);
            flag = false;
        }
        return flag;
    }

    public static boolean updateBasket(List<BasketItem> basketItemList, Context context) {
        boolean flag = false;
        try {
            String content = new Gson().toJson(basketItemList);
            flag = CacheManager.write(CacheManager.CART_SHOPPING_KEY, content, context);
        } catch (Exception e) {
            Log.e(BasketCacheManager.class.getName(), "更新菜篮子缓存出错", e);
            flag = false;
        }
        return flag;
    }

    public static List<BasketItem> getBasket(Context context) {
        List<BasketItem> basketItems = new ArrayList<BasketItem>();
        String content = CacheManager.read(CacheManager.CART_SHOPPING_KEY, context);
        if (StringUtils.isEmpty(content)) {
            return basketItems;
        }
        basketItems = new Gson().fromJson(content, new TypeToken<ArrayList<BasketItem>>() {
        }.getType());

        return basketItems;
    }

}
