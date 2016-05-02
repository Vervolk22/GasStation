package com.andreyzholudev.gasstation.dataaccess.entities;

import javax.persistence.Basic;
import javax.persistence.Column;

/**
 * Created by AndreyZholudev on 5/2/2016.
 */
public class SimpleUserEntityWithPassword extends SimpleUserEntity {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
