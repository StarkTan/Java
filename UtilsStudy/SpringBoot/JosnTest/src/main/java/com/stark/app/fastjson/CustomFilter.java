package com.stark.app.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

/**
 * Created by Strak on 2017/5/14.
 * 自己定义的过滤器
 */
public class CustomFilter implements PropertyPreFilter
{
    private static String[] excluds;
    private static final AntPathMatcher matcher = new AntPathMatcher();
    private String path = "";
    public CustomFilter(String[] excluds)
    {
        this.excluds = excluds;
    }
    //野路子核心，使用jsonSerializer的String对象，判断最后字符 思路是错误的
    @Override
    public boolean apply(JSONSerializer jsonSerializer, Object o, String s)
    {
        String str = jsonSerializer.toString();
        if (str.isEmpty()||str.endsWith("[")) {
            path = path+'/'+s;
        } else if (str.endsWith(":")) {
            path = path + '/' + s;
        } else if (str.endsWith("}")||str.endsWith("]")) {
            while (str.endsWith("}")||str.endsWith("]"))
            {
                path = StringUtils.substringBeforeLast(path, "/");
                str = StringUtils.substringBeforeLast(str,str.endsWith("}")?"}":"]");
            }
            path = StringUtils.substringBeforeLast(path, "/")+'/'+s;
        }else{
            path = StringUtils.substringBeforeLast(path, "/") + '/' + s;
        }
        if (pathMatch(excluds, path)) {
            if (str.endsWith(":")||str.endsWith("[")||str.isEmpty())
            {
                path = StringUtils.substringBeforeLast(path, "/");
            }
            return false;
        }
        return true;
    }
    private static boolean pathMatch(String[] excluds, String path)
    {
        for (String exclud : excluds) {
            if (matcher.match(exclud, path)) return true;
        }
        return false;
    }


}
