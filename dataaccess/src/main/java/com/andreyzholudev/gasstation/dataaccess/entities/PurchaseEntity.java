package com.andreyzholudev.gasstation.dataaccess.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Time;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "purchase", schema = "gasstationdb", catalog = "")
public class PurchaseEntity extends BaseEntity {
    private int amount;
    private int paid;
    private Time time;
    private DayEntity day;
    private ClientEntity client;
    private FuelEntity fuel;
    private CashierEntity cashier;

    @Basic
    @Column(name = "amount")
    @Min(value = 1, message = "Amount cannot be less than 1.")
    @Max(value = 200, message = "Amount cannot be bigger than 200.")
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

    @ManyToOne
    @JoinColumn(name = "day_id")
    public DayEntity getDay() {
        return day;
    }

    public void setDay(DayEntity day) {
        this.day = day;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    public FuelEntity getFuel() {
        return fuel;
    }

    public void setFuel(FuelEntity fuel) {
        this.fuel = fuel;
    }

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    public CashierEntity getCashier() {
        return cashier;
    }

    public void setCashier(CashierEntity cashier) {
        this.cashier = cashier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseEntity that = (PurchaseEntity) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (paid != that.paid) return false;
        if (day != that.day) return false;
        if (fuel != that.fuel) return false;
        if (cashier != that.cashier) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        result = 31 * result + paid;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (day != null ? time.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (fuel != null ? time.hashCode() : 0);
        result = 31 * result + (cashier != null ? time.hashCode() : 0);
        return result;
    }
}
