package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.AuthorityEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.UserEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndreyZholudev on 3/18/2016.
 */
@Repository
public class UserDAO extends DAOImpl<UserEntity> implements UserDetailsService {
    @Override
    protected Class getEntityClass() {
        return UserEntity.class;
    }

    /*public BaseEntity read(String username) {
        return (BaseEntity)(getCurrentSession().createCriteria(UserEntity.class).
                add(Restrictions.eq("username", username)).uniqueResult());
    }*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserDetails details;
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        UserEntity user = (UserEntity)(getCurrentSession().createCriteria(UserEntity.class).
                add(Restrictions.eq("username", username)).uniqueResult());

        for (AuthorityEntity authority : user.getAuthorities()) {
            auths.add(new SimpleGrantedAuthority(authority.getRole()));
        }
        details = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), auths);
        return details;
    }

}
