package com.andreyzholudev.gasstation.dataaccess.services;

import com.andreyzholudev.gasstation.dataaccess.dal.AbstractDAO;
import com.andreyzholudev.gasstation.dataaccess.dal.UserDAO;
import org.springframework.stereotype.Service;

/**
 * Created by AndreyZholudev on 3/22/2016.
 */
@Service
public abstract class AbstractLoader {
    protected static AbstractDAO abstractDAO;

    public AbstractDAO getAbstractDAO() {
        return abstractDAO;
    }
    public void setUserDAO(UserDAO abstractDAO) {
        this.abstractDAO = abstractDAO;
    }
}
