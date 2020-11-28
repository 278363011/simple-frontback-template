package com.codebaobao.controller;


import com.codebaobao.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public Result<String> index() {
        return Result.success("测试成功");
    }
}
