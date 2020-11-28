package com.codebaobao.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(this.authticationJwtfilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public Filter authticationJwtfilter() {
        return new AuthticationJwtfilter();
    }
}
