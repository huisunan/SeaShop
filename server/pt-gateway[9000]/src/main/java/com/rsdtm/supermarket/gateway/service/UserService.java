package com.rsdtm.supermarket.gateway.service;

import com.rsdtm.supermarket.gateway.bo.User;
import com.rsdtm.supermarket.gateway.client.UmsClient;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UmsClient userClient;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UmsUserVo body = null;
        try {
             body = userClient.getUserByName(s).getBody();
            User user = new User();
            BeanUtils.copyProperties(body,user);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (UmsRole role : body.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            user.setAuthorities(authorities);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("用户名错误:"+s);
        }
    }
}
