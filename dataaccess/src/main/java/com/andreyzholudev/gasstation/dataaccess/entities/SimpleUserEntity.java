package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;

/**
 * Created by AndreyZholudev on 4/1/2016.
 */
@Entity
@Table(name = "user", schema = "gasstationdb", catalog = "")
public class SimpleUserEntity extends BaseEntity {
    private String username;
    private CashierEntity cashier;

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleUserEntity that = (SimpleUserEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @OneToOne(mappedBy = "simpleUser")
    public CashierEntity getCashier() {
        return cashier;
    }

    public void setCashier(CashierEntity cashier) {
        this.cashier = cashier;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}