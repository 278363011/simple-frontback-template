package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebaobao.Constants.Constant;
import com.codebaobao.mapper.RoleMapper;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.Role;
import com.codebaobao.modeldo.PageVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    RoleMapper roleMapper;

    //增
    @RequestMapping("/add")
    public Result<String> addRole(@Valid @RequestBody final Role role) {
        try {
            final Role sqlRole = this.roleService.getById(role.getId());
            if (Objects.nonNull(sqlRole)) {
                return Result.error(CodeMsg.create(10000, Constant.INSERT_ERROR));
            } else {
                if (this.roleService.save(role)) {
                    // 清空缓存
                    AdminLoginController.roelMap.clear();
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
    public Result<String> deleteRole(@NotNull final Integer id) {
        try {
            final Role sqlRole = this.roleService.getById(id);
            if (Objects.isNull(sqlRole)) {
                return Result.error(CodeMsg.create(10000, Constant.DELETE_ERROR));
            } else {
                if (this.roleService.removeById(id)) {
                    // 清空缓存
                    AdminLoginController.roelMap.clear();
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
            final Role sqlRole = this.roleService.getById(doctor.getId());
            if (Objects.isNull(sqlRole)) {
                return Result.error(CodeMsg.create(10000, Constant.UPDATE_ERROR));
            } else {
                if (this.roleService.updateById(sqlRole)) {
                    // 清空缓存
                    AdminLoginController.roelMap.clear();
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
            final Role sqlRole = this.roleService.getById(id);
            if (Objects.isNull(sqlRole)) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(sqlRole);
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
            final IPage<Role> rolePage = new Page<>(pageVo.getPageNow(), pageVo.getPageSize());
            this.roleMapper.selectPage(rolePage, null);
            final List<Role> roleList = rolePage.getRecords();
            if (Objects.isNull(roleList) || roleList.isEmpty()) {
                return Result.error(CodeMsg.create(10000, Constant.SELECT_ERROR));
            } else {
                return Result.success(roleList);
            }
        } catch (final Exception e) {
            log.error(Constant.SELECT_EXCEPTION, e);
            return Result.success(Constant.SELECT_SUCCESS);
        }
    }


}
