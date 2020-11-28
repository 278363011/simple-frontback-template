package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.Constants.Constant;
import com.codebaobao.mapper.SchoolAreaMapper;
import com.codebaobao.model.SchoolArea;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.SchoolAreaService;
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
@RequestMapping("/area")
public class SchoolAreaController {

    @Autowired
    SchoolAreaService schoolAreaService;
    @Autowired
    SchoolAreaMapper schoolAreaMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addSchoolArea(@Valid @RequestBody final SchoolArea schoolArea) {
        try {
            final SchoolArea sqlSchoolArea = this.schoolAreaService.getById(schoolArea.getId());
            if (Objects.nonNull(sqlSchoolArea)) {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            } else {
                if (this.schoolAreaService.save(schoolArea)) {
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
    public Result<String> deleteSchoolArea(@NotNull final Integer id) {
        try {
            final SchoolArea sqlSchoolArea = this.schoolAreaService.getById(id);
            if (Objects.isNull(sqlSchoolArea)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.schoolAreaService.removeById(id)) {
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
    public Result<String> updateSchoolArea(@Valid @RequestBody final SchoolArea schoolArea) {
        try {
            final SchoolArea sqlSchoolArea = this.schoolAreaService.getById(schoolArea.getId());
            if (Objects.isNull(sqlSchoolArea)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.schoolAreaService.updateById(schoolArea)) {
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
    public Result<Object> selectSchoolAreaById(@NotNull final Integer id) {
        try {
            final SchoolArea sqlSchoolArea = this.schoolAreaService.getById(id);
            if (Objects.isNull(sqlSchoolArea)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlSchoolArea);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllSchoolAreaByPage(@Valid final PageVo pageVo) {
        try {
            final IPage<SchoolArea> schoolAreaPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.schoolAreaMapper.selectPage(schoolAreaPage, null);
            final List<SchoolArea> schoolAreaList = schoolAreaPage.getRecords();
            if (Objects.isNull(schoolAreaList) || schoolAreaList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(schoolAreaList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}
