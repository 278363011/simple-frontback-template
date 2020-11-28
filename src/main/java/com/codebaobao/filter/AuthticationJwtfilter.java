package com.codebaobao.filter;

import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.utils.JwtTokenUtil;
import com.codebaobao.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthticationJwtfilter implements Filter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        final String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "");
        System.out.println(path);
        if (StringUtils.equalsIgnoreCase(path, "/user/login")) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            final String authHeader = httpRequest.getHeader(this.jwtTokenUtil.getHeader());
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                final String authToken = authHeader.substring("Bearer ".length());
                if (!this.jwtTokenUtil.isTokenExpired(authToken)) {
                    chain.doFilter(servletRequest, servletResponse);
                } else {
                    ResponseUtils.renderToken(httpResponse, Result.error(CodeMsg.create(10001, "token过期!")));
                }
            } else {
                ResponseUtils.renderToken(httpResponse, Result.error(CodeMsg.create(10002, "请输入token!")));
                return;
            }
        }

    }

    @Override
    public void destroy() {
        //do nothing
    }
}
