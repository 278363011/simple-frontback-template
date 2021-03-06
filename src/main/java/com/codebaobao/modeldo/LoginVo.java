package com.codebaobao.modeldo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Accessors
@AllArgsConstructor
public class LoginVo {
    @NotNull(message = "用户名不能为空")
    String userName;
    @NotNull(message = "密码不能为空")
    String passWord;
    String verification_code;
    String sms_code;
}
