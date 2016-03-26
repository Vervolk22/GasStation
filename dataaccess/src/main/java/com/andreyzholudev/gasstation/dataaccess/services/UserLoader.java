package com.andreyzholudev.gasstation.dataaccess.services;

import com.andreyzholudev.gasstation.dataaccess.dal.UserDAO;
import com.andreyzholudev.gasstation.dataaccess.entities.Authority;
import com.andreyzholudev.gasstation.dataaccess.entities.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class for providing user credentials to spring security from database.
 */
public class UserLoader extends AbstractLoader implements UserDetailsService {
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserDetails details;
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        User user = ((UserDAO)abstractDAO).getUser(username);

        for (Authority authority : user.getAuthorities()) {
            auths.add(new SimpleGrantedAuthority(authority.getRole()));
        }
        details = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExprired(), user.isAccountNonLocked(), auths);
        return details;
    }
}
