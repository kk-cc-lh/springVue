package com.kk.novel.security.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class JwtUser implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private String state;
    //权限
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long userId, String username, String password, String state,  List<GrantedAuthority> authorities){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.state = state;
        this.authorities = authorities ;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return !"0".equals(state);
    }

    // 密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @Override
    public boolean isEnabled() {
        return true;
    }

    //获得当前登陆用户对应的对象。
    public static JwtUser getCurUser(){
        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println("当前用户:"+userDetails);
        return userDetails;
    }
}
