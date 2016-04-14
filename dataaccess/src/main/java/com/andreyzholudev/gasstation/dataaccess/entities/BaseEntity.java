package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Andrei on 28.03.2016.
 */
@MappedSuperclass
public abstract class BaseEntity {
    protected int id;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
