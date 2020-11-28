package com.codebaobao.test;

import com.codebaobao.utils.PwdUtils;

import java.io.UnsupportedEncodingException;

public class PwdTest {
    public static void main(final String[] args) throws UnsupportedEncodingException {
        System.out.println(PwdUtils.encryption("123456", "1234"));
    }

}
