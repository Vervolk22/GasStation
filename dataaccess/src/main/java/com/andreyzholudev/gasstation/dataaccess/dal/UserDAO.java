package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.AuthorityEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return UserEntity.class;
    }

    /*public BaseEntity read(String username) {
        return (BaseEntity)(getCurrentSession().createCriteria(UserEntity.class).
                add(Restrictions.eq("username", username)).uniqueResult());
    }*/

    public int createByQuery(UserEntity userEntity) {
        try (Session session = factory.openSession()) {
            Query query = session.createSQLQuery("insert into user (username, password, enabled, " +
                    "accountNonExpired, credentialsNonExpired, accountNonLocked) values(:a, :b, :c, :d, :e, :f)");
            query.setParameter("a", userEntity.getUsername());
            query.setParameter("b", userEntity.getPassword());
            query.setParameter("c", true);
            query.setParameter("d", true);
            query.setParameter("e", true);
            query.setParameter("f", true);
            query.executeUpdate();
        }

        try (Session session = factory.openSession()) {
            Query query = session.createSQLQuery("SELECT id FROM user WHERE username = :a");
            query.setParameter("a", userEntity.getUsername());
            int t = (Integer)query.uniqueResult();
            return t;
        }
    }

    @Transactional
    public UserEntity readByUsername(String username) {
        try (Session session = factory.openSession()) {
            return (UserEntity) session.createCriteria(UserEntity.class).
                    add(Restrictions.eq("username", username)).uniqueResult();
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        UserDetails details;
        try(Session session = factory.openSession()) {
            List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
            UserEntity user = (UserEntity) (session.createCriteria(UserEntity.class).
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

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
