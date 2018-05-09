package com.stark.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2017/11/10.
 * 带登录的访问
 */
public class Demo_2 {
    public static void main(String[] args) throws Exception {
        BasicCookieStore cookieStore = new BasicCookieStore();
        try (CloseableHttpClient httpclient =
                     HttpClients.custom().setDefaultCookieStore(cookieStore).build()) {
            //使用get请求登录页面的cookie和csrf的token
            HttpUriRequest getLogin = RequestBuilder.get()
                    .setUri(new URI("http://localhost:8082/login")).build();
            List<Cookie> cookies;
            StringBuilder sb;
            String csrf;
            HttpEntity entity;
            try (CloseableHttpResponse getReponse = httpclient.execute(getLogin)) {
                cookies = cookieStore.getCookies();
                sb = new StringBuilder();
                for (Cookie cookie : cookies) {
                    sb.append(cookie.getName()).append('=').append(cookie.getValue()).append(';');
                }
                entity = getReponse.getEntity();
                String html = EntityUtils.toString(entity, "UTF-8");
                Document parse = Jsoup.parse(html);
                Elements elements = parse.getElementsByAttributeValue("name", "_csrf");
                csrf = elements.get(0).attr("content");
                EntityUtils.consume(entity);
            }
            RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false).build();
            HttpUriRequest login = RequestBuilder.post().setUri(new URI("http://localhost:8082/login"))
                    .addHeader("Cookie", sb.toString()).setConfig(requestConfig)
                    .addHeader("X-CSRF-TOKEN", csrf)
                    .addParameter("username", "USI002").addParameter("password", "123456").build();

            int statusCode;
            try (CloseableHttpResponse response = httpclient.execute(login)) {
                statusCode = response.getStatusLine().getStatusCode();
                cookies = cookieStore.getCookies();
                sb = new StringBuilder();
                for (Cookie cookie : cookies) {
                    sb.append(cookie.getName()).append('=').append(cookie.getValue()).append(';');
                }
            }
            if (statusCode == 302) {
                System.out.println("模拟登录成功，访问页面");
                HttpUriRequest visit = RequestBuilder.get().setUri(new URI("http://localhost:8082/devices"))
                        .addHeader("Cookie", sb.toString()).build();
                try (CloseableHttpResponse response = httpclient.execute(visit)) {
                    statusCode = response.getStatusLine().getStatusCode();
                    System.out.println(statusCode);
                    entity = response.getEntity();
                    System.out.println(EntityUtils.toString(entity, "UTF-8"));
                    EntityUtils.consume(entity);
                }
            }
        }
    }
}
