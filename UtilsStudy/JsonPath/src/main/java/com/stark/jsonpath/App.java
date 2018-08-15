package com.stark.jsonpath;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//使用jsonpath
public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        App.class.getResource("/test.json")
                                .openStream()));
        String line = reader.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        JSONObject object = JSONObject.parseObject(sb.toString());
        System.out.println(JSONPath.eval(object, "$.store.book[0:].author"));
    }
}
