package com.stark.controller.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Strak on 2017/5/16
 * 测试请求
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomController.class)
public class CustomControllerTest
{
    @Autowired
    private MockMvc mvc;

    @Test
    public void testBinderRequest() throws Exception
    {
        //测试为不同的对象增加前缀，定向绑定
        this.mvc.perform(get("/custom/binder").accept(MediaType.ALL)
                .param("simple.name", "simple").param("simple.age", "23").param("simple.sex", "true")
                .param("single.name", "single").param("single.number", "23"))
                .andExpect(status().isOk()).andExpect(content().string("name:simple,age:23,boy:yes,I'm Single named single number is 23"));
    }
    @Test
    public void testCustomResponse() throws Exception
    {
        //测试自定义复杂json解析
        String response ="{\"cycle\":{\"id\":\"cycle1\",\"name\":\"outer\",\"object\":{\"id\":\"cycle2\",\"name\":\"inner\"}},\"id\":\"main\",\"list\":[{\"name\":\"list1\"},{\"name\":\"list2\"}],\"name\":\"mainObject\",\"single\":{\"id\":\"simple_object\",\"name\":\"simple\"}}";
        this.mvc.perform(get("/custom/jsonreturntwo").accept(MediaType.ALL))
                .andExpect(status().isOk()).andExpect(content().string(response));
        //测试自定义json解析
        this.mvc.perform(get("/custom/jsonreturn").accept(MediaType.ALL))
                .andExpect(status().isOk()).andExpect(content().string("{\"name\":\"simple\",\"sex\":true}"));
        //测试拦截生成自定义响应
        this.mvc.perform(get("/custom/response").accept(MediaType.ALL))
                .andExpect(status().isOk()).andExpect(content().string("专属控制"));
    }
    @Test
    public void testCustomRequest() throws Exception
    {
        //测试拦截生成自定义参数
        this.mvc.perform(get("/custom/request").accept(MediaType.ALL))
                .andExpect(status().isOk()).andExpect(content().string("{\"name\":\"simple\",\"age\":23,\"sex\":true}"));
    }
}