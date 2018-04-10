package com.stark.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

/**
 * Created by Stark on 2017/11/14.
 * 使用代理访问
 */
public class Demo_3 {
    public static void main(String[] args) {
        try {
            String url = "https://www.baidu.com/";
            // 使用默认配置创建httpclient的实例
            CloseableHttpClient client = HttpClients.createDefault();
            HttpHost proxy = new HttpHost("113.65.20.137", 9999, "http");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setRedirectsEnabled(true).build();
            HttpUriRequest get = RequestBuilder.get().setUri(new URI(url))
                    .setConfig(requestConfig).build();

            CloseableHttpResponse response = client.execute(get);
            int status_code = response.getStatusLine().getStatusCode();
            System.out.println(status_code);
            // 释放资源
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
