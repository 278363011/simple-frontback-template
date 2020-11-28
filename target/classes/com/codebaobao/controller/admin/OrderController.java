package com.codebaobao.controller.admin;


import com.codebaobao.result.Result;
import com.codebaobao.service.SchoolAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    SchoolAreaService schoolAreaService;

    //增
    @RequestMapping("/add")
    public Result<String> addSchoolArea() {

        return Result.success("添加校区成功");
    }

    //删
    @RequestMapping("/delete")
    public Result<String> deleteSchoolArea() {

        return Result.success("添加校区成功");
    }

    //改
    @RequestMapping("/update")
    public Result<String> updateSchoolArea() {

        return Result.success("添加校区成功");
    }

    //查
    @RequestMapping("/query")
    public Result<String> selectSchoolArea() {

        return Result.success("添加校区成功");
    }


}
