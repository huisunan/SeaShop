package com.rsdtm.supermarket.gateway.compent;

import com.alibaba.fastjson.JSON;
import com.rsdtm.supermarket.gateway.bo.User;
import com.rsdtm.supermarket.gateway.config.JwtProperties;
import com.rsdtm.supermarket.gateway.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${pt.jwt.header}")
    private String tokenHeader;

    @Value("${pt.jwt.tokenHead}")
    private String authTokenStart;

    @Autowired
    JwtProperties jwtProperties;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader(tokenHeader);

        if (StringUtils.isNotEmpty(authToken) && authToken.startsWith(authTokenStart)) {
            authToken = authToken.substring(authTokenStart.length());
//            log.info("请求" + request.getRequestURI() + "携带的token:" + authToken);
            try {
                User infoFromToken = JwtUtils.getInfoFromToken(authToken, jwtProperties.getPublicKey());
                log.info("url:"+request.getRequestURI()+",用户名:"+infoFromToken.getUsername());
                if (infoFromToken.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(infoFromToken, null, infoFromToken.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (ExpiredJwtException e) {
                sendErrorMessage(response);
                return;
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        } else {
            log.info("游客访问，IP为："+request.getRemoteAddr());
        }

        filterChain.doFilter(request, response);

    }

    private void sendErrorMessage(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        Map<String, Object> result = new HashMap<>();
        response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
        result.put("message", "身份信息过期，请重新登录");
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}