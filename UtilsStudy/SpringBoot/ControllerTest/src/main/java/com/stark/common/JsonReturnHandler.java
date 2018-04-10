package com.stark.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.stark.app.fastjson.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by Strak on 2017/5/15.
 *
 */
public class JsonReturnHandler implements HandlerMethodReturnValueHandler
{
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter)
    {
        return methodParameter.getMethodAnnotation(JsonExclude.class)!=null;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception
    {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse nativeResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        nativeResponse.setContentType("text/plain;charset=utf-8");
        JsonExclude jsonFilter = methodParameter.getMethodAnnotation(JsonExclude.class);
        String[] excludes = jsonFilter.excludes();
        CustomFilter filter = new CustomFilter(excludes);
        String jsonReponse = JSONObject.toJSONString(o,filter, SerializerFeature.WriteMapNullValue);
        nativeResponse.getWriter().write(jsonReponse);
    }
}
