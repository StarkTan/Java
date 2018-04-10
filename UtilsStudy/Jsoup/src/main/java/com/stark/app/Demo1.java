package com.stark.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Stark on 2017/11/13.
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Temp\\proxy.html");
        Document parse = Jsoup.parse(file, "UTF-8");
        Elements ips = parse.getElementsByAttributeValue("data-title", "IP");
        List<String> list1 = ips.eachText();
        Elements ports = parse.getElementsByAttributeValue("data-title", "PORT");
        List<String> list2 = ports.eachText();
        System.out.println(list1);
        System.out.println(list2);
    }
}
