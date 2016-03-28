package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "supply", schema = "gasstationdb", catalog = "")
public class SupplyEntity {
    private int id;
    private int amount;
    private int paid;
    private Time time;
    private int dayId;
    private int fuelId;
    private int branchId;
    private int supplierId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "paid")
    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    @Basic
    @Column(name = "time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "day_id")
    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    @Basic
    @Column(name = "fuel_id")
    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }

    @Basic
    @Column(name = "branch_id")
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    @Basic
    @Column(name = "supplier_id")
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupplyEntity that = (SupplyEntity) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (paid != that.paid) return false;
        if (dayId != that.dayId) return false;
        if (fuelId != that.fuelId) return false;
        if (branchId != that.branchId) return false;
        if (supplierId != that.supplierId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        result = 31 * result + paid;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + dayId;
        result = 31 * result + fuelId;
        result = 31 * result + branchId;
        result = 31 * result + supplierId;
        return result;
    }
}
