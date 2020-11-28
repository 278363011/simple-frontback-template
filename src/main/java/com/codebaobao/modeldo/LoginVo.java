package com.codebaobao.modeldo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors
public class LoginVo {
    String userName;
    String passWord;
    String verification_code;
    String sms_code;
}
