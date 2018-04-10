package com.stark.controller.controller;

import com.stark.common.JsonExclude;
import com.stark.entity.ComplexObject;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Strak on 2017/5/14.
 * 测试复杂请求的contoller
 */
@RequestMapping("/complex_request")
@RestController
public class ComplexRequestController
{
    @RequestMapping("/params")
    @JsonExclude
    public Object testParams(@ModelAttribute ComplexObject object)
    {
        return object;
    }

    @RequestMapping("/json_params")
    public Object testJsonParams(@RequestBody ComplexObject object)
    {
        return object;
    }

}
