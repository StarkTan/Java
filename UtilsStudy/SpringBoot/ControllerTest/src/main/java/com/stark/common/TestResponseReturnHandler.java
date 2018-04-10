package com.stark.common;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Strak on 2017/5/16.
 *
 */
public class TestResponseReturnHandler implements HandlerMethodReturnValueHandler
{
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter)
    {
        return methodParameter.getMethodAnnotation(TestResponse.class)!=null;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception
    {
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse nativeResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        nativeResponse.setContentType("text/plain;charset=utf-8");
        nativeResponse.getWriter().write("专属控制");
    }
}
