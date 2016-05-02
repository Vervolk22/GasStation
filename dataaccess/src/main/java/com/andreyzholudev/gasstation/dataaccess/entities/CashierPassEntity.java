package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "cashier", schema = "gasstationdb", catalog = "")
public class CashierPassEntity extends BaseEntity {
    private String name;
    private Date startdate;
    private BranchEntity branch;
    private SimpleUserEntityWithPassword simpleUser;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "startdate")
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @ManyToOne
    @JoinColumn(name = "branch_id")
    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branch) {
        this.branch = branch;
    }

    @Transient
    public SimpleUserEntityWithPassword getSimpleUser() {
        return simpleUser;
    }

    public void setSimpleUser(SimpleUserEntityWithPassword simpleUser) {
        this.simpleUser = simpleUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashierPassEntity that = (CashierPassEntity) o;

        if (id != that.id) return false;
        if (branch != null ? !branch.equals(that.branch) : that.branch != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + branch.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getName();
    }
}
