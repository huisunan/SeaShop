package com.rsdtm.supermarket.gateway.compent;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class PtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        Map<String, Object> result = new HashMap<>();
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result.put("message", "用户名或密码输入错误，登录失败!");
        } else if (e instanceof DisabledException) {
            result.put("message", "账户被禁用，登录失败，请联系管理员!");
        } else {
            result.put("message", "尚未登录!");
        }
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
    }
}