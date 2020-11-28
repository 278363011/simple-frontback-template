package com.codebaobao.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codebaobao.exception.UserNameAndPwdErrorException;
import com.codebaobao.exception.UserNotFoundException;
import com.codebaobao.exception.iIllegalStateException;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.Role;
import com.codebaobao.modeldo.LoginVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.DoctorService;
import com.codebaobao.service.RoleService;
import com.codebaobao.utils.JwtTokenUtil;
import com.codebaobao.utils.PwdUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/admin")
public class AdminLoginController {

    //本地role的缓存
    public static Map<Integer, String> roelMap = new HashMap<>();

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    DoctorService doctorService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/login")
    public Result<String> adminLogin(@RequestBody final LoginVo loginVo) {

        if (Objects.isNull(loginVo) || StringUtils.isBlank(loginVo.getUserName()) || StringUtils.isBlank(loginVo.getPassWord())) {
            throw new UserNameAndPwdErrorException("账号密码不能为空");
        }

        final Doctor doctorInfo = this.doctorService.getOne(new QueryWrapper<Doctor>()
                .eq("account_name", loginVo.getUserName()));

        if (Objects.isNull(doctorInfo)) {
            throw new UserNotFoundException("账号为空");
        }

        if (roelMap.isEmpty()) {
            final List<Role> list = this.roleService.list();
            if (Objects.isNull(list) || list.isEmpty()) {
                throw new iIllegalStateException("数据库角色信息");
            }
            roelMap = list.stream().collect(Collectors.toMap(Role::getId, Role::getRoleName));
        }

        if (PwdUtils.comparePassword(doctorInfo.getPwd(), loginVo.getPassWord(), doctorInfo.getSalt())) {

            final Map<String, Object> claims = new HashMap<>();
            claims.put("username", doctorInfo.getAccountName());
            claims.put("role", roelMap.get(doctorInfo.getRoleId()));
            return Result.success(this.jwtTokenUtil.generateToken(null));
        } else {
            return Result.error(CodeMsg.create(500, "密码错误"));
        }

    }


}
