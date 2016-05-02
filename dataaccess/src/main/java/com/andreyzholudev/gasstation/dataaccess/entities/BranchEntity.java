package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "branch", schema = "gasstationdb", catalog = "")
public class BranchEntity extends BaseEntity {
    private Date startdate;
    private AddressEntity address;

    @Basic
    @Column(name = "startdate")
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @ManyToOne
    @JoinColumn(name = "address_id")
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BranchEntity that = (BranchEntity) o;

        if (id != that.id) return false;
        if (address.getId() != that.address.getId()) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + address.getId();
        return result;
    }
}
