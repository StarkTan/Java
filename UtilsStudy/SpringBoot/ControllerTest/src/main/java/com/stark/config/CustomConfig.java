package com.stark.config;

import com.stark.common.JsonReturnHandler;
import com.stark.common.TestRequestResolver;
import com.stark.common.TestResponseReturnHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Strak on 2017/5/16.
 */
@Configuration
public class CustomConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(new TestRequestResolver());
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers)
    {
        returnValueHandlers.add(new TestResponseReturnHandler());
        returnValueHandlers.add(new JsonReturnHandler());
    }

}
