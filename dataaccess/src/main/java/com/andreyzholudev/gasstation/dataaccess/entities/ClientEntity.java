package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "client", schema = "gasstationdb", catalog = "")
public class ClientEntity extends BaseEntity {
    private int id;
    private String name;
    private int bonus;
    private int totalSpent;
    private int monthSpent;

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
    @Column(name = "bonus")
    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Basic
    @Column(name = "total_spent")
    public int getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(int totalSpent) {
        this.totalSpent = totalSpent;
    }

    @Basic
    @Column(name = "month_spent")
    public int getMonthSpent() {
        return monthSpent;
    }

    public void setMonthSpent(int monthSpent) {
        this.monthSpent = monthSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (id != that.id) return false;
        if (bonus != that.bonus) return false;
        if (totalSpent != that.totalSpent) return false;
        if (monthSpent != that.monthSpent) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + bonus;
        result = 31 * result + totalSpent;
        result = 31 * result + monthSpent;
        return result;
    }
}
