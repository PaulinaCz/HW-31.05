package com.czerniecka.dao.implementation;

import com.czerniecka.config.DBConnectionConfig;
import com.czerniecka.dao.DogDao;
import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;



public class DogDaoImplTest {

    @Test
    public void addDogTest() {

        Dog dog = new Dog("Silver", "Terrier");
        Owner owner = new Owner("Scott", "MALE", "Athens", "Athenian", "1-2-3", dog);

        Assert.assertEquals(Dog.ID_OF_NOT_PERSISTENT_DOG, dog.getId());

        Connection connection = DBConnectionConfig.getInstance().getConnection();
        DogDao dogDao = new DogDaoImpl(connection);

        dogDao.addDog(dog, owner);

        Assert.assertTrue(dog.getId() != Dog.ID_OF_NOT_PERSISTENT_DOG);

    }

    @Test
    public void deleteDogByIdTest() {

        Connection connection = DBConnectionConfig.getInstance().getConnection();
        DogDao dogDao = new DogDaoImpl(connection);

        int expected = 1;
        int result = dogDao.deleteDog(1);


        Assert.assertEquals(expected, result);


    }

    @Test
    public void getDogByOwnerId() {

        Connection connection = DBConnectionConfig.getInstance().getConnection();
        DogDao dogDao = new DogDaoImpl(connection);

        Dog dog = dogDao.findDogByOwnerId(2);
        String dogName = "Wolf";

        Assert.assertEquals(dogName, dog.getDogName());

    }


}