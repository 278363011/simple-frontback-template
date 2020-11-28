package com.codebaobao.exception;


import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserNameAndPwdErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<CodeMsg> handlerUserNameAndPwdErrorException(final UserNameAndPwdErrorException ex) {
        return Result.error(CodeMsg.create(10003, ex.getMessage()));
    }

}
