package com.codebaobao.controller.admin;


import com.codebaobao.model.Doctor;
import com.codebaobao.result.Result;
import com.codebaobao.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    //增
    @RequestMapping("/add")
    public Result<String> addSchoolArea(final Doctor doctor) {
        try {
            if (this.doctorService.save(doctor)) {
                return Result.success("添加医生成功");
            } else {
                return Result.success("添加医生失败");
            }

        } catch (final Exception e) {
            log.error("添加医生失败", e);
            return Result.success("添加医生发生异常");
        }
    }

    //删
    @RequestMapping("/delete")
    public Result<String> deleteSchoolArea(final Integer id) {

        try {
            if (this.doctorService.removeById(id)) {
                return Result.success("删除医生成功");
            } else {
                return Result.success("删除医生失败");
            }
        } catch (final Exception e) {
            log.error("删除医生失败", e);
            return Result.success("删除医生发生异常");
        }
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
