package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "fuel", schema = "gasstationdb", catalog = "")
public class FuelEntity extends BaseEntity {
    private String name;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuelEntity that = (FuelEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getName();
    }
}
