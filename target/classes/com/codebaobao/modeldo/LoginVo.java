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
    @NotNull
    String userName;
    @NotNull
    String passWord;
    @NotNull
    String verification_code;
    @NotNull
    String sms_code;
}
