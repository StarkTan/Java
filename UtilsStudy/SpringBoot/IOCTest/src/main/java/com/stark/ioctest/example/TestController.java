package com.stark.ioctest.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Stark on 2017/11/30.
 */
@Controller
public class TestController {

    @Value("${name}") //获取properties中的值
    private String name;
    @Autowired //直接从IOC中获取Service_1实例，没有则报错
    private Service_1 service1;

    @Bean(name = "service2")
    private IService getService() {
        return new Service_2();
    }

    @Resource(name = "service2")
    private IService service2;
    @Autowired
    private Config config;

    @RequestMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("GBK");
        PrintWriter writer = response.getWriter();
        writer.println("从配置文件中获取name的值：" + name);
        writer.println("Service_1的名称：" + service1.getServiceName());
        writer.println("Service_2的名称：" + service2.getServiceName());
        writer.println("Config的属性:" + config.getName() + " , " + config.getAge());
        writer.close();
    }

}
