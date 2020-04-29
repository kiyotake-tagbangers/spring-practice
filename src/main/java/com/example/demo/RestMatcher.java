package com.example.demo;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class RestMatcher implements RequestMatcher {

    private AntPathRequestMatcher matcher;

    public RestMatcher(String url) {
        super();
        matcher = new AntPathRequestMatcher(url);
    }

    @Override
    public boolean matches(HttpServletRequest request) {

        // GETメソッドならCSRFのチェックをしない
        if ("GET".equals(request.getMethod())) {
            return false;
        }

        // 特定のURLの場合、CSRFチェックしない
        if (matcher.matches(request)) {
            return false;
        }

        return true;
    }
}
