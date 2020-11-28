package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.Constants.Constant;
import com.codebaobao.mapper.DoctorMapper;
import com.codebaobao.model.Doctor;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorMapper doctorMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addDoctor(@Valid @RequestBody final Doctor doctor) {
        try {
            final Doctor sqlDoctor = this.doctorService.getById(doctor.getId());
            if (Objects.nonNull(sqlDoctor)) {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            } else {
                if (this.doctorService.save(doctor)) {
                    return Result.success(Constant.INSERT_SUCCESS);
                } else {
                    return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
                }
            }
        } catch (final Exception e) {
            log.error(Constant.INSERT_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.INSERT_EXCEPTION));
        }
    }

    //删
    @RequestMapping("/delete")
    public Result<String> deleteDoctor(@NotNull final Integer id) {
        try {
            final Doctor sqlDoctor = this.doctorService.getById(id);
            if (Objects.isNull(sqlDoctor)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.doctorService.removeById(id)) {
                    return Result.success(Constant.DELETE_SUCCESS);
                } else {
                    return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
                }
            }
        } catch (final Exception e) {
            log.error(Constant.DELETE_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.DELETE_EXCEPTION));
        }
    }

    //改
    @RequestMapping("/update")
    public Result<String> updateDoctor(@Valid @RequestBody final Doctor doctor) {
        try {
            final Doctor sqlDoctor = this.doctorService.getById(doctor.getId());
            if (Objects.isNull(sqlDoctor)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.doctorService.updateById(doctor)) {
                    return Result.success(Constant.UPDATE_SUCCESS);
                } else {
                    return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
                }
            }
        } catch (final Exception e) {
            log.error(Constant.UPDATE_EXCEPTION, e);
            return Result.error(CodeMsg.create(10000, Constant.UPDATE_EXCEPTION));
        }
    }

    //查
    @RequestMapping("/queryById")
    public Result<Object> selectDoctorById(@NotNull final Integer id) {
        try {
            final Doctor doctor = this.doctorService.getById(id);
            if (Objects.isNull(doctor)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(doctor);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllDoctorByPage(@Valid final PageVo pageVo) {
        try {
            final IPage<Doctor> doctorPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.doctorMapper.selectPage(doctorPage, null);
            final List<Doctor> doctorList = doctorPage.getRecords();
            if (Objects.isNull(doctorList) || doctorList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(doctorList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}
