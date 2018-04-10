package com.stark.antpathmatcher;

import org.springframework.util.AntPathMatcher;

/**
 * Created by Strak on 2017/5/14.
 * 测试使用AntPathMather
 */
public class AntPathMatcherApp
{
    public static final AntPathMatcher matcher = new AntPathMatcher();


    public static void main(String[] args)
    {
        String pattern = "**/stark/tan/*/hao";
        String path = "stark/tan/si/hao/";
        boolean match = matcher.match(pattern, path);
        System.out.println(match);
    }
}
