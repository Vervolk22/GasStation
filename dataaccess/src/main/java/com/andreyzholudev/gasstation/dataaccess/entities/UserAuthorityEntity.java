package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AndreyZholudev on 4/14/2016.
 */
@Entity
@Table(name = "user_authority", schema = "", catalog = "gasstationdb")
public class UserAuthorityEntity extends BaseEntity {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthorityEntity that = (UserAuthorityEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
