package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AndreyZholudev on 4/1/2016.
 */
@Entity
@Table(name = "user", schema = "gasstationdb", catalog = "")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private Set<AuthorityEntity> authorities = new HashSet<AuthorityEntity>(0);

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "accountNonExpired")
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonexpired) {
        this.accountNonExpired = accountNonexpired;
    }

    @Basic
    @Column(name = "credentialsNonExpired")
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonexpired) {
        this.credentialsNonExpired = credentialsNonexpired;
    }

    @Basic
    @Column(name = "accountNonLocked")
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonlocked) {
        this.accountNonLocked = accountNonlocked;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "authority_id",
                    nullable = false, updatable = false) })
    public Set<AuthorityEntity> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (enabled != that.enabled) return false;
        if (id != that.id) return false;
        if (accountNonExpired != null ? !accountNonExpired.equals(that.accountNonExpired) : that.accountNonExpired != null)
            return false;
        if (accountNonLocked != null ? !accountNonLocked.equals(that.accountNonLocked) : that.accountNonLocked != null)
            return false;
        if (credentialsNonExpired != null ? !credentialsNonExpired.equals(that.credentialsNonExpired) : that.credentialsNonExpired != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (accountNonExpired != null ? accountNonExpired.hashCode() : 0);
        result = 31 * result + (credentialsNonExpired != null ? credentialsNonExpired.hashCode() : 0);
        result = 31 * result + (accountNonLocked != null ? accountNonLocked.hashCode() : 0);
        return result;
    }
}
