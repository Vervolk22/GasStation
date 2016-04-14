package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Andrei on 28.03.2016.
 */
@Entity
@Table(name = "day", schema = "gasstationdb", catalog = "")
public class DayEntity extends BaseEntity {
    private Date date;

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayEntity dayEntity = (DayEntity) o;

        if (id != dayEntity.id) return false;
        if (date != null ? !date.equals(dayEntity.date) : dayEntity.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getDate().toString();
    }
}
