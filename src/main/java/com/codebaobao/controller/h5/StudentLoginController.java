package com.codebaobao.controller.h5;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codebaobao.controller.admin.AdminLoginController;
import com.codebaobao.exception.UserNotFoundException;
import com.codebaobao.exception.iIllegalStateException;
import com.codebaobao.model.Role;
import com.codebaobao.model.Student;
import com.codebaobao.modeldo.LoginVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.RoleService;
import com.codebaobao.service.StudentService;
import com.codebaobao.utils.JwtTokenUtil;
import com.codebaobao.utils.PwdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-22
 */
@RestController
@RequestMapping("/student")
public class StudentLoginController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    StudentService studentService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/login")
    public Result<String> dockLogin(@Valid @RequestBody final LoginVo loginVo) {

        final Student studentInfo = this.studentService.getOne(new QueryWrapper<Student>()
                .eq("account_name", loginVo.getUserName()));

        if (Objects.isNull(studentInfo)) {
            throw new UserNotFoundException("账号为空");
        }

        if (AdminLoginController.roelMap.isEmpty()) {
            final List<Role> list = this.roleService.list();
            if (Objects.isNull(list) || list.isEmpty()) {
                throw new iIllegalStateException("数据库角色信息");
            }
            AdminLoginController.roelMap = list.stream().collect(Collectors.toMap(Role::getId, Role::getRoleName));
        }

        if (PwdUtils.comparePassword(studentInfo.getPwd(), loginVo.getPassWord(), studentInfo.getSalt())) {
            final Map<String, Object> claims = new HashMap<>();
            claims.put("username", studentInfo.getAccountName());
            claims.put("role", AdminLoginController.roelMap.get(studentInfo.getRoleId()));
            return Result.success(this.jwtTokenUtil.generateToken(null));
        } else {
            return Result.error(CodeMsg.create(500, "密码错误"));
        }

    }
}
