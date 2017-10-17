package com.b2b.web.util;

import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class GsonUtil {
	
	@SuppressWarnings("serial")
	public static String jsonResult2String(JsonResult result){
		
		if(result == null) return "";
		
		Gson gson = new Gson();
		Type t = new TypeToken<JsonResult>() {}.getType();
		
		return gson.toJson(result, t);
	}
	

}
