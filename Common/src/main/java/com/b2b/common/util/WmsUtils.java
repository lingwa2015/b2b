package com.b2b.common.util;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Created by a. on 2017/10/10.
 */
public class WmsUtils {

    private static final String  APP_KEY = "201710101027";

    private static final String SECRET_KEY = "MY201710101027";

    private static final String LOGINID = "01";

    public static String getHttpResponse4Post(String method,String requestXml) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpClient client = new DefaultHttpClient();
            String urlStr = "http://155o313k01.iask.in:12983/BH_CLIS/qimen";

            Map<String, String> requestParamter = getRequestParameter(method, APP_KEY, LOGINID);

            String sign = sign(requestParamter, requestXml, SECRET_KEY);

            requestParamter.put("sign", sign);

            urlStr = urlStr + "?" + joinRequestParams(requestParamter);

            System.out.println(urlStr);

            URL url = new URL(urlStr);

            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            setURLConnection(conn, requestXml);

            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                System.out.println("连接成功");
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                String a = null;
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    a = new String(data1);
                    System.out.println(a);

                    return a;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                System.out.println("no++");
            }
        } catch (Exception e) {
            System.out.println("请求发生异常，异常信息抛出");
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return " ";
    }

    public static String sign(Map<String, String> params , String body, String secretKey) {

        // 1. 第一步，确保参数已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 2. 第二步，把所有参数名和参数值拼接在一起(包含body体)
        String joinedParams = joinRequestParams(params, body, secretKey, keys);

        System.out.println("body: " +body);

        // 3. 第三步，使用加密算法进行加密（目前仅支持md5算法）
        String signMethod = params.get("sign_method");
        if (!"md5".equalsIgnoreCase(signMethod)) {
            return null;
        }

        byte[] abstractMesaage = digest(joinedParams);

        // 4. 把二进制转换成大写的十六进制
        String sign = byte2Hex(abstractMesaage);

        System.out.println("sign: " +sign);

        return sign;

    }

    private static String joinRequestParams(Map<String, String> params, String body,
                                            String secretKey, String[] sortedKes) {
        StringBuilder sb = new StringBuilder(secretKey); // 前面加上secretKey
        for (String key : sortedKes) {
            if ("sign".equals(key)) {
                continue; // 签名时不计算sign本身

            } else {
                String value = params.get(key);
                if (isNotEmpty(key) && isNotEmpty(value)) {
                    sb.append(key).append(value);
                }
            }
        }
        sb.append(body); // 拼接body体
        sb.append(secretKey); // 最后加上secretKey
        return sb.toString();
    }

    private static byte[] digest(String message) {
        try {
            MessageDigest md5Instance = MessageDigest.getInstance("MD5");
            md5Instance.update(message.getBytes("UTF-8"));
            return md5Instance.digest();
        } catch (UnsupportedEncodingException e) {
            return null;

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String byte2Hex(byte[] bytes) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };

        int j = bytes.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (byte byte0 : bytes) {
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    public static String joinRequestParams(Map<String, String> params)
    {
        StringBuilder sb = new StringBuilder(); // 前面加上secretKey
        int foreachTotal = 0;
        for (String key : params.keySet())
        {
            String value = strToUrlEncoder2(params.get(key));
            if (isNotEmpty(key) && isNotEmpty(value))
            {
                if (foreachTotal == 0)
                {
                    sb.append(key).append("=").append(value);
                }
                else
                {
                    sb.append("&").append(key).append("=").append(value);
                }
                foreachTotal++;
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static String strToUrlEncoder(String value) {
        String result = value;
        try {
            URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
//            logger.error("请求参数转换成urlencode时异常", e);
        }
        return result;
    }

    private static String strToUrlEncoder2(String value) {
        String result = value;
        try
        {
            result = URLEncoder.encode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
        }
        return result;
    }

    public static Map<String, String> getRequestParameter(String callMethod, String appKey, String loginId)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> requestParamter = new HashMap<String, String>();
        requestParamter.put("method", strToUrlEncoder(callMethod));
        requestParamter.put("format", strToUrlEncoder("xml"));
        requestParamter.put("app_key", strToUrlEncoder(appKey));
        requestParamter.put("v", strToUrlEncoder("2.0"));
        requestParamter.put("sign_method", strToUrlEncoder("md5"));
        requestParamter.put("customerId", strToUrlEncoder(loginId));
        requestParamter.put("timestamp", strToUrlEncoder(sdf.format(new Date())));

        return requestParamter;

    }

    public static void setURLConnection(HttpURLConnection conn, String requestXml) throws Exception{

        // 设置允许输出
        conn.setDoOutput(true);

        conn.setDoInput(true);
        // 设置不用缓存
        conn.setUseCaches(false);
        // 设置传递方式
        conn.setRequestMethod("POST");
        // 设置维持长连接
        conn.setRequestProperty("Connection", "Keep-Alive");
        // 设置文件字符集:
        conn.setRequestProperty("Charset", "UTF-8");
        //转换为字节数组
        byte[] data = requestXml.getBytes();
        // 设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        // 设置文件类型:
        conn.setRequestProperty("contentType", "text/xml");
        // 开始连接请求
        conn.connect();
        OutputStream out = conn.getOutputStream();
        // 写入请求的字符串
        out.write(data);
        out.flush();
        out.close();
    }
}
