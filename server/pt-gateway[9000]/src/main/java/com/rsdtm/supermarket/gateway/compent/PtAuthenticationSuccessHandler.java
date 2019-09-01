package com.rsdtm.supermarket.gateway.compent;

import com.alibaba.fastjson.JSON;
import com.rsdtm.supermarket.gateway.bo.User;
import com.rsdtm.supermarket.gateway.client.UmsClient;
import com.rsdtm.supermarket.gateway.config.JwtProperties;
import com.rsdtm.supermarket.gateway.utils.JwtUtils;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    UmsClient umsClient;

    @Value("${pt.jwt.header}")
    private String tokenHeader;

    @Value("${pt.jwt.tokenHead}")
    private String authTokenStart;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        UmsUserVo userByName = umsClient.getUserByName(authentication.getName()).getBody();
        User user = new User(userByName);

        Map<String,Object> result = new HashMap<>();
        try {
            String token = JwtUtils.generateToken(user, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            response.setStatus(HttpServletResponse.SC_OK);
            result.put("token",authTokenStart+" "+token);
            result.put("tokenHeader",tokenHeader);
            result.put("username",user.getName());
            result.put("id",user.getId());
            result.put("phone",user.getPhone());
            result.put("staffId",user.getStaffId());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            result.put("msg",e.getMessage());
        }
        String jsonString = JSON.toJSONString(result);
        response.getWriter().write(jsonString);
        response.getWriter().flush();
    }
}
