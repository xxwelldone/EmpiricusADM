package br.com.empiricus.EmpiricusADM.Security;


import br.com.empiricus.EmpiricusADM.Model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;


@Getter
public class UserPrincipal {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(User user) {
        this.username = user.getCpf();
        this.password = user.getPassword();

        if (user.isEh_admin()) {
            this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_PADRAO"));
        }
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user);
    }
}