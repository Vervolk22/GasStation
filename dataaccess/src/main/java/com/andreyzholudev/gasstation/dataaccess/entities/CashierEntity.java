package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "cashier", schema = "gasstationdb", catalog = "")
public class CashierEntity extends BaseEntity {
    private int id;
    private String name;
    private Date startdate;
    private int branchId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Basic
    @Column(name = "branch_id")
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashierEntity that = (CashierEntity) o;

        if (id != that.id) return false;
        if (branchId != that.branchId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + branchId;
        return result;
    }

    @Override
    public String toString() {
        return getName();
    }
}
