package com.czerniecka.dao.implementation;

import com.czerniecka.dao.OwnerDao;
import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;

import java.sql.Connection;
import java.util.List;

public class OwnerDaoImpl  implements OwnerDao {

    private Connection dbConnection;

    public OwnerDaoImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean addOwner(Owner owner, Dog dog) {

        boolean result = false;





        return result;
    }

    @Override
    public int deleteOwner(int ownerId) {
        return 0;
    }

    @Override
    public int updateOwner(int ownerId) {
        return 0;
    }

    @Override
    public List<Owner> getAllOwners() {
        return null;
    }
}
