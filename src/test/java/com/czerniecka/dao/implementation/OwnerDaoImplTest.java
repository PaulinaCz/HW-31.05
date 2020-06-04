package com.czerniecka.dao.implementation;

import com.czerniecka.config.DBConnectionConfig;
import com.czerniecka.dao.DogDao;
import com.czerniecka.dao.OwnerDao;
import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class OwnerDaoImplTest {

    @Test
    public void addOwnerTest(){

        Dog dog = new Dog("Golden", "Golden Retriever");
        Owner owner = new Owner("Matt", "MALE", "Cracow", "Poludniowa", "30-123", dog);

        Assert.assertEquals(Owner.ID_OF_NOT_PERSISTENT_PERSON, owner.getId());

        Connection connection = DBConnectionConfig.getInstance().getConnection();
        DogDao dogDao = new DogDaoImpl(connection);
        OwnerDao ownerDao = new OwnerDaoImpl(connection, dogDao);

        ownerDao.addOwner(owner, dog);
        dogDao.addDog(dog, owner);

        Assert.assertTrue(owner.getId() != Owner.ID_OF_NOT_PERSISTENT_PERSON);

    }

    @Test
    public void getAllOwnersTest(){

        Connection connection = DBConnectionConfig.getInstance().getConnection();
        OwnerDao ownerDao = new OwnerDaoImpl(connection, new DogDaoImpl(connection));

        List<Owner> owners = ownerDao.getAllOwners();

        Assert.assertTrue(owners.size() > 0);

    }

    @Test
    public void deleteOwnerByIdTest(){

        Connection connection = DBConnectionConfig.getInstance().getConnection();
        OwnerDao ownerDao = new OwnerDaoImpl(connection, new DogDaoImpl(connection));

        int expected = 1;
        int result = ownerDao.deleteOwner(1);

        Assert.assertEquals(expected, result);

    }


}