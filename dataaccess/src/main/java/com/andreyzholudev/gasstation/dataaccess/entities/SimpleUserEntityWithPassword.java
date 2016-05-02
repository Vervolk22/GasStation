package com.andreyzholudev.gasstation.dataaccess.entities;

/**
 * Created by AndreyZholudev on 5/2/2016.
 */
public class SimpleUserEntityWithPassword extends SimpleUserEntity {
    private String password;

    public SimpleUserEntityWithPassword() {

    }

    public SimpleUserEntityWithPassword(SimpleUserEntity simpleUserEntity) {
        this.setId(simpleUserEntity.getId());
        this.setUsername(simpleUserEntity.getUsername());
        this.setCashier(simpleUserEntity.getCashier());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
