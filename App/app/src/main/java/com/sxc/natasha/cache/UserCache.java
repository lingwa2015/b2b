package com.sxc.natasha.cache;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sxc.natasha.common.CacheManager;
import com.sxc.natasha.common.StringUtils;
import com.sxc.natasha.domain.User;

import java.lang.reflect.Type;

/**
 * Created by bobo on 15/3/20.
 */
public class UserCache {

   static String USER_CACHE_KEY ="user_cache";
   static Type userTypeToken = new TypeToken<User>(){}.getType();

   public static User getUser(Context context){
       User u = null;
       String cacheStr = CacheManager.read(USER_CACHE_KEY,context);

       if(StringUtils.isEmpty(cacheStr)) return  u;

       Gson gson = new Gson();
       try{
           u = gson.fromJson(cacheStr,userTypeToken);
       }catch (Exception e){
           Log.e("getUser",e.toString());
           return u;
       }


       return u;
   }

    public static void saveUser(String cacheStr,Context context){

        CacheManager.write(USER_CACHE_KEY,cacheStr,context);
    }

}
