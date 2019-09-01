package com.rsdtm.supermarket.gateway.bo;

import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class User implements UserDetails {

    private Integer id;

    private String password;

    private String name;

    private Byte enable;

    private String phone;

    private Integer staffId;

    private List<GrantedAuthority> authorities;


    public User(Integer id,String username,List<GrantedAuthority> authorities){
        this.id = id;
        this.name = username;
        this.authorities = authorities;

    }
//    public User(String username,List<GrantedAuthority> authorities){
//
//        this.name = username;
//        this.authorities = authorities;
//
//    }

    public User(UmsUserVo userVo){
        this.name =userVo.getName();
        this.password = userVo.getPassword();
        this.id = userVo.getId();
        this.enable = userVo.getEnable();
        this.phone = userVo.getPhone();
        this.staffId = userVo.getStaffId();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UmsRole role : userVo.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        this.setAuthorities(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return BooleanUtils.toBoolean(enable);
    }
}
