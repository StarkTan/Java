package com.stark.controller.controller;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Strak on 2017/5/13.
 * 测试简单的对象传递
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SimpleController.class)
public class SimpleControllerTest
{
    @Autowired
    private MockMvc mvc;

    /**
     * 测试请求方式
     */
    @Test
    public void testMethod() throws Exception
    {
        //测试Post传递
        this.mvc.perform(post("/methodPost")
                .param("params", "params")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    /**
     * 简单参数个数修改
     *
     * @throws Exception
     */
    @Test
    public void testSimpleParams() throws Exception
    {
        //传递两个简单参数
        this.mvc.perform(get("/twoparams")
                .param("params", "params").param("time", "3")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("paramsparamsparams"));

        //传递一个简单参数
        this.mvc.perform(get("/params").param("params", "firsttest")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("firsttest"));

        //检查测试成功
        this.mvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("HelloTest"));
    }


}