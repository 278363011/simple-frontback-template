package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.Constants.Constant;
import com.codebaobao.mapper.RotationChartMapper;
import com.codebaobao.model.RotationChart;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.RotationChartService;
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
@RequestMapping("/rotation")
public class RotationChartController {

    @Autowired
    RotationChartService rotationChartService;
    @Autowired
    RotationChartMapper rotationChartMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addRotation(@Valid @RequestBody final RotationChart rotationChart) {
        try {
            final RotationChart sqlRotationChart = this.rotationChartService.getById(rotationChart.getId());
            if (Objects.nonNull(sqlRotationChart)) {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            } else {
                if (this.rotationChartService.save(rotationChart)) {
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
    public Result<String> deleteRotation(@NotNull final Integer id) {
        try {
            final RotationChart sqlRotationChart = this.rotationChartService.getById(id);
            if (Objects.isNull(sqlRotationChart)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.rotationChartService.removeById(id)) {
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
    public Result<String> updateRotation(@Valid @RequestBody final RotationChart rotationChart) {
        try {
            final RotationChart sqlRotationChart = this.rotationChartService.getById(rotationChart.getId());
            if (Objects.isNull(sqlRotationChart)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.rotationChartService.updateById(rotationChart)) {
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
    public Result<Object> selectRotationById(@NotNull final Integer id) {
        try {
            final RotationChart sqlRotationChart = this.rotationChartService.getById(id);
            if (Objects.isNull(sqlRotationChart)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlRotationChart);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllRotationByPage(@Valid final PageVo pageVo) {
        try {
            final IPage<RotationChart> rotationPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.rotationChartMapper.selectPage(rotationPage, null);
            final List<RotationChart> rotationChartList = rotationPage.getRecords();
            if (Objects.isNull(rotationChartList) || rotationChartList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(rotationChartList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}
