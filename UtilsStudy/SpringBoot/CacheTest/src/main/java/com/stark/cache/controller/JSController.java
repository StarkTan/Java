package com.stark.cache.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by Stark on 2018/3/19.
 * js 文件获取缓存
 */
@RequestMapping()
@Controller
public class JSController {

    @RequestMapping("/js")
    public void getJS(HttpServletResponse response, String path) throws IOException {
        ClassPathResource resource = new ClassPathResource("/static/lib/js/" + path);
        File file = resource.getFile();
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        System.out.println(bytes.length);
        inputStream.read(bytes);
        long now = new Date().getTime();
        long next = now + 60000;
        Date date = new Date(next);
        response.addHeader("Expires", date.toString());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

}
