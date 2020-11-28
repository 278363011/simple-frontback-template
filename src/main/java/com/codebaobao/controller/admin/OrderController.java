package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.Constants.Constant;
import com.codebaobao.mapper.PsychologicalOrderMapper;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.PsychologicalOrder;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.PsychologicalOrderService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    PsychologicalOrderService orderService;
    @Autowired
    PsychologicalOrderMapper orderMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addOrder(@Valid @RequestBody final PsychologicalOrder order) {
        try {
            final PsychologicalOrder sqlOrder = this.orderService.getById(order.getId());
            if (Objects.nonNull(sqlOrder)) {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            } else {
                if (this.orderService.save(order)) {
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
    public Result<String> deleteOrder(@NotNull final Integer id) {
        try {
            final PsychologicalOrder sqlOrder = this.orderService.getById(id);
            if (Objects.isNull(sqlOrder)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.orderService.removeById(id)) {
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
    public Result<String> updateOrder(@Valid @RequestBody final Doctor doctor) {
        try {
            final PsychologicalOrder sqlOrder = this.orderService.getById(doctor.getId());
            if (Objects.isNull(sqlOrder)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.orderService.updateById(sqlOrder)) {
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
    public Result<Object> selectOrderById(@NotNull final Integer id) {
        try {
            final PsychologicalOrder sqlOrder = this.orderService.getById(id);
            if (Objects.isNull(sqlOrder)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlOrder);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllOrderByPage(@Valid final PageVo pageVo) {
        try {
            final IPage<PsychologicalOrder> orderPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.orderMapper.selectPage(orderPage, null);
            final List<PsychologicalOrder> orderList = orderPage.getRecords();
            if (Objects.isNull(orderList) || orderList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(orderList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}
