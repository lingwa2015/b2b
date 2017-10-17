package com.sxc.natasha.common;

/**
 * Description: PhoneNumUtil 处理手机号码的工具类
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    PhoneNumUtil.java
 * Create at:   2015-03-25
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-03-25      zhengshutian    1.0         1.0 Version
 */
public class PhoneNumUtil {

    /**
     * 拼接短信提示信息
     *
     * @author zhengshutian
     *
     */
    public static String buildPhoneMessage(String phone) {

        String previous="我们已经向您";

        String next="的手机发送了一条短信验证码";

        return previous+hidePhoneNum(phone)+next;
    }

    /**
     * 隐藏手机号码
     *
     * @author zhengshutian
     *
     */
    public static String hidePhoneNum(String phone){

        StringBuilder sb = new StringBuilder(phone);

        if(phone.length() >= 11){
            sb.replace(3,7,"****");
        }

        return sb.toString();
    }
}
