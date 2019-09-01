package com.rsdtm.supermarket.gateway.compent;

import com.alibaba.fastjson.JSON;
import com.rsdtm.supermarket.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {



    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setCharacterEncoding("UTF-8");
        Map<String,Object> result = new HashMap<>();
        result.put("message","权限不足，请联系管理员");
        resp.getWriter().write(JSON.toJSONString(result));
        resp.getWriter().flush();
    }
}