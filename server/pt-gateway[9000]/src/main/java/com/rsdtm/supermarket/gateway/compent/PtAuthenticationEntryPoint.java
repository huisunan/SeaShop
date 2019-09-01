package com.rsdtm.supermarket.gateway.compent;

import com.alibaba.fastjson.JSON;
import com.rsdtm.supermarket.gateway.exception.PtExpiredJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");

        Map<String, Object> result = new HashMap<>();


        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        result.put("message", "访问被拒绝，请先登录");


        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();

    }
}
