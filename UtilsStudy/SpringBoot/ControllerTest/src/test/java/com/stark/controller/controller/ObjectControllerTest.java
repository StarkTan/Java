package com.stark.controller.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Stark on 2017/5/13.
 * 测试对象的传递
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ObjectController.class)
public class ObjectControllerTest
{
    @Autowired
    private MockMvc mvc;

    //测试@PathVariable参数传递
    @Test
    public void testRequestAttribute() throws Exception
    {
        this.mvc.perform(post("/object/path_variable/stark/23/true")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:23,boy:yes"));
        this.mvc.perform(post("/object/path_variable/stark/23/false")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:23,boy:no"));
    }

    //测试json数据参数
    @Test
    public void testJsonSimple() throws Exception
    {
        //完整json数据
        this.mvc.perform(post("/object/jsonobj")
                .content("{\"name\":\"stark\",\"age\":23,\"sex\":true}") //转义：哭
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:23,boy:yes"));
        //多余json数据,可以多余
        this.mvc.perform(post("/object/jsonobj")
                .content("{\"name\":\"stark\",\"age\":23,\"sex\":true,\"flag\":\"flag\"}") //转义：哭
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:23,boy:yes"));
        //json数据属性有缺失
        this.mvc.perform(post("/object/jsonobj")
                .content("{\"name\":\"stark\",\"sex\":true}") //转义：哭
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:null,boy:yes"));
        //json数据混合基本数据传输
        this.mvc.perform(post("/object/json_mix_primite")
                .content("{\"name\":\"stark\",\"sex\":true}") //转义：哭
                .param("flag","flag")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:null,boy:yes,flag:flag"));
        //json数据混合基本数据传输
        this.mvc.perform(get("/object/json_mix_obj")
                .content("{\"name\":\"stark\",\"sex\":true}") //转义：哭
                .param("name", "stark").param("age", "23").param("sex", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:null,boy:yes,name:stark,age:23,boy:yes"));
    }

    //混合式传入参数
    @Test
    public void testSimpleMix() throws Exception
    {
        //键值对传输
        //同时填充值
        this.mvc.perform(get("/object/simplemix")
                .param("name", "stark").param("age", "23")
                .param("sex", "true").param("flag", "test"))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:23,boy:yes,flag:test,name:stark"));
        //会对所有的对象的对应属性进行赋值
        this.mvc.perform(get("/object/simpletwoobj")
                .param("name", "stark").param("age", "23")
                .param("sex", "true").param("flag", "test"))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:23,boy:yes,name:stark,age:23,boy:yes"));
    }

    //测试简单的对象传递
    @Test
    public void testSimple() throws Exception
    {
        //普通键值对传值ok,可以有值残缺
        this.mvc.perform(get("/object/simple")
                .param("name", "stark")//.param("age","23")
                .param("sex", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("name:stark,age:null,boy:yes"));

        //带参数名传递失败
        this.mvc.perform(get("/object/simple")
                .param("object.name", "stark").param("object.age", "23").param("object.sex", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("name:null,age:null,boy:no"));
    }
}