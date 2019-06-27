package org.post.microservice.auth.jwt;

import org.assertj.core.util.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(String username, String password) {
        return new JwtUser(1L, username,"", "", "", password, mapToGrantedAuthorities(
                Lists.list("admin")), true, new Date());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        List<GrantedAuthority> res = new ArrayList<>();

        for (String authority : authorities) res.add(new SimpleGrantedAuthority(authority));

        return res;
    }
}
