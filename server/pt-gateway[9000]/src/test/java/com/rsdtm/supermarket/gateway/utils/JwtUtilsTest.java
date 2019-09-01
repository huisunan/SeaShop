package com.rsdtm.supermarket.gateway.utils;

import com.rsdtm.supermarket.gateway.bo.User;
import com.rsdtm.supermarket.gateway.client.UmsClient;
import com.rsdtm.supermarket.gateway.config.JwtProperties;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilsTest {
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UmsClient umsClient;

    @Test
    public void generateToken() throws Exception {
        ResponseEntity<UmsUserVo> userById = umsClient.getUserById(5);
        System.out.println(userById);
        User user = new User();
        BeanUtils.copyProperties(userById.getBody(),user);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UmsRole role : userById.getBody().getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        user.setAuthorities(authorities);


        String s = JwtUtils.generateToken(user, jwtProperties.getPrivateKey(), 120);
        System.out.println(s);

    }

    @Test
    public void getInfoFromToken() throws Exception {
        String s = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6NSwidXNlcm5hbWUiOiJodWlzdW5hbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJhZG1pbiJ9LHsiYXV0aG9yaXR5Ijoicm9vdCJ9XSwiZXhwIjoxNTYyMDY1MjgyfQ.ly_wtXiugOuDkU6PfgzqpPipXGKla3joI76ya_hmuf36K1eH-QoH9cPMixBz2_pu2u3V3Y7N6HEt4FLGN-H6PrHIk93v_aOBWelP4GUzrsf1a0a-FUwlpexI5lmk8wfG7UFYGIKu1Od4HMvDE4C5_zgxturFo-yB7Ppw7MDzj1w";
        User infoFromToken = JwtUtils.getInfoFromToken(s, jwtProperties.getPublicKey());
        System.out.println(infoFromToken);
    }
}