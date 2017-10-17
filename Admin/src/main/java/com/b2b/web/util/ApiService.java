package com.b2b.web.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
    @Autowired(required = false)
    private CloseableHttpClient httpclient;

    @Autowired(required = false)
    private RequestConfig requestConfig;
    public String doGet(String url) throws ClientProtocolException, IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("执行GET请求,url:{}", url);
        }
        try {
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("获取响应内容,content={},url={}", content, url);
                }
                return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
    public String doPostParam(String url, Map<String, String> param) throws ClientProtocolException,
            IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (null != param) {
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (Entry<String, String> entry : param.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            httpPost.setEntity(formEntity);
        }
        CloseableHttpResponse response = null;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("执行POST请求,url:{}", url);
        }
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("获取响应内容,content={},url={}", content, url);
                }
                return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
    
    public String doPostXml(String url, String xml) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (null != xml) {
            StringEntity stringEntity = new StringEntity(xml,"utf-8");
            httpPost.setEntity(stringEntity);
        }
        CloseableHttpResponse response = null;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("执行POSTxml请求,url:{}", url);
        }
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("获取响应内容,content={},url={}", content, url);
                }
                return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
}
