package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "everyday_price_info", schema = "gasstationdb", catalog = "")
public class EverydayPriceInfoEntity extends BaseEntity {
    private int id;
    private int fuelPrice;
    private int dayId;
    private int fuelId;
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
    @Column(name = "fuel_price")
    public int getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EverydayPriceInfoEntity that = (EverydayPriceInfoEntity) o;

        if (id != that.id) return false;
        if (fuelPrice != that.fuelPrice) return false;
        if (dayId != that.dayId) return false;
        if (fuelId != that.fuelId) return false;
        if (branchId != that.branchId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fuelPrice;
        result = 31 * result + dayId;
        result = 31 * result + fuelId;
        result = 31 * result + branchId;
        return result;
    }
}
