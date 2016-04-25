package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "everyday_price_info", schema = "gasstationdb", catalog = "")
public class EverydayPriceInfoEntity extends BaseEntity {
    private int fuelPrice;
    private DayEntity dayEntity;
    private FuelEntity fuelEntity;
    private BranchEntity branchEntity;

    @Basic
    @Column(name = "fuel_price")
    public int getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    @ManyToOne
    @JoinColumn(name = "day_id")
    public DayEntity getDayEntity() {
        return dayEntity;
    }

    public void setDayEntity(DayEntity dayEntity) {
        this.dayEntity = dayEntity;
    }

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    public FuelEntity getFuelEntity() {
        return fuelEntity;
    }

    public void setFuelEntity(FuelEntity fuelEntity) {
        this.fuelEntity = fuelEntity;
    }

    @ManyToOne
    @JoinColumn(name = "branch_id")
    public BranchEntity getBranchEntity() {
        return branchEntity;
    }

    public void setBranchEntity(BranchEntity branchEntity) {
        this.branchEntity = branchEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EverydayPriceInfoEntity that = (EverydayPriceInfoEntity) o;

        if (id != that.id) return false;
        if (fuelPrice != that.fuelPrice) return false;
        if (dayEntity.getId() != that.dayEntity.getId()) return false;
        if (fuelEntity.getId() != that.fuelEntity.getId()) return false;
        if (branchEntity.getId() != that.branchEntity.getId()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fuelPrice;
        result = 31 * result + dayEntity.getId();
        result = 31 * result + fuelEntity.getId();
        result = 31 * result + branchEntity.getId();
        return result;
    }
}
