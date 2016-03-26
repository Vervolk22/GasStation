package com.andreyzholudev.gasstation.dataaccess.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Class for providing user credentials to spring security. Only for tests, contans
 * some predefined users.
 */
public class UserLoaderTest implements UserDetailsService {
    public UserDetails loadUserByUsername(String username) {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        switch(username) {
            case "admin":
                auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                return new User(username, "pass", true, true, true, true,  auths);
            case "user1":
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                return new User(username, "1111", true, true, true, true,  auths);
            case "user2":
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                return new User(username, "2222", true, true, true, true,  auths);
        }
        return null;
    }
}
