package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.Constants.Constant;
import com.codebaobao.mapper.PsychologicalRoomMapper;
import com.codebaobao.model.PsychologicalRoom;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.PsychologicalRoomService;
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
@RequestMapping("/room")
public class SchoolRoomController {

    @Autowired
    PsychologicalRoomService psychologicalRoomService;
    @Autowired
    PsychologicalRoomMapper psychologicalRoomMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addPsychologicalRoom(@Valid @RequestBody final PsychologicalRoom psychologicalRoom) {
        try {
            final PsychologicalRoom sqlPsychologicalRoom = this.psychologicalRoomService.getById(psychologicalRoom.getId());
            if (Objects.nonNull(sqlPsychologicalRoom)) {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            } else {
                if (this.psychologicalRoomService.save(psychologicalRoom)) {
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
    public Result<String> deletePsychologicalRoom(@NotNull final Integer id) {
        try {
            final PsychologicalRoom psychologicalRoom = this.psychologicalRoomService.getById(id);
            if (Objects.isNull(psychologicalRoom)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.psychologicalRoomService.removeById(id)) {
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
    public Result<String> updatePsychologicalRoom(@Valid @RequestBody final PsychologicalRoom psychologicalRoom) {
        try {
            final PsychologicalRoom sqlPsychologicalRoom = this.psychologicalRoomService.getById(psychologicalRoom.getId());
            if (Objects.isNull(sqlPsychologicalRoom)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.psychologicalRoomService.updateById(sqlPsychologicalRoom)) {
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
    public Result<Object> selectPsychologicalRoomById(@NotNull final Integer id) {
        try {
            final PsychologicalRoom psychologicalRoom = this.psychologicalRoomService.getById(id);
            if (Objects.isNull(psychologicalRoom)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(psychologicalRoom);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }

    //分页查
    @RequestMapping("/queryByPage")
    public Result<Object> selectAllPsychologicalRoomByPage(@Valid final PageVo pageVo) {
        try {
            final IPage<PsychologicalRoom> psychologicalRoomPage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.psychologicalRoomMapper.selectPage(psychologicalRoomPage, null);
            final List<PsychologicalRoom> psychologicalRoomList = psychologicalRoomPage.getRecords();
            if (Objects.isNull(psychologicalRoomList) || psychologicalRoomList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(psychologicalRoomList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}
