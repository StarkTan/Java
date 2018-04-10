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
 * Created by Strak on 2017/5/13.
 * 测试复杂对象的传递
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ComplexRequestController.class)
public class ComplexRequestControllerTest
{
    @Autowired
    private MockMvc mvc;

    //测试简单的对象传递
    @Test
    public void testSimple() throws Exception
    {
        //普通键值对传值ok,可以有值残缺
        String response = "{\"name\":\"stark\",\"single\":{\"name\":\"single\",\"number\":100},\"set\":[],\"list\":[{\"name\":\"list1\",\"number\":null},{\"name\":null,\"number\":300}]}";
        this.mvc.perform(get("/complex_request/params")
                .param("name", "stark")
                .param("single.name", "single").param("single.number", "100")
                //.param("set[0].name","set1").param("set[1].number","200") 不提供构造参数无法填充参数
                .param("list[0].name","list1").param("list[1].number","300"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        String jsonResponse = "{\"name\":\"stark\",\"single\":{\"name\":\"single\",\"number\":100},\"set\":[{\"name\":null,\"number\":200},{\"name\":\"set1\",\"number\":null}],\"list\":[{\"name\":\"list1\",\"number\":null},{\"name\":null,\"number\":300}]}";
        //json数据请求（set无序）
        this.mvc.perform(post("/complex_request/json_params")
                .content(jsonResponse) //转义：哭
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonResponse));
    }
}