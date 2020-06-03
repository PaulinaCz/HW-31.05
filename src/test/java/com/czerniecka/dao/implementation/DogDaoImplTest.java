package com.czerniecka.dao.implementation;

import com.czerniecka.config.DBConnectionConfig;
import com.czerniecka.dao.DogDao;
import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;



public class DogDaoImplTest {


    /*@Before
    public void initData(){

        Connection dbConnection = DBConnectionConfig.getInstance().getConnection();
        String deleteOwnersQuery = "DELETE FROM JDBC_HOMEWORK.OWNER";
        String initOwnersQuery = "" +
               " INSERT INTO JDBC_HOMEWORK.OWNER (NAME, SEX, CITY, STREET, POSTCODE)                   \n" +
               " VALUES ('Anna', 'FEMALE', 'London', 'Queens', 'SW1 1AB');                             \n" +
               " INSERT INTO JDBC_HOMEWORK.OWNER (NAME, SEX, CITY, STREET, POSTCODE)                   \n" +
               " VALUES ('Mary', 'FEMALE', 'Slough', 'High Street', 'AR1 1RA');                         \n" +
               " INSERT INTO JDBC_HOMEWORK.OWNER (NAME, SEX, CITY, STREET, POSTCODE)                   \n" +
               " VALUES ('John', 'MALE', 'Warsaw', 'Polnocna', '00-123');                              \n" ;

        String deleteDogsQuery = "DELETE FROM JDBC_HOMEWORK.DOG";
        String initDogsQuery = "" +
                "INSERT INTO JDBC_HOMEWORK.DOG (NAME, BREED, OWNER_ID) VALUES ('Puppy', 'Poodle', 3);       " +
                "INSERT INTO JDBC_HOMEWORK.DOG (NAME, BREED, OWNER_ID) VALUES ('Wolf', 'Husky', 2);         " +
                "INSERT INTO JDBC_HOMEWORK.DOG (NAME, BREED, OWNER_ID) VALUES ('Fluffy', 'Pomeranian', 1);  " ;

        //remove all data
        try {
            PreparedStatement deleteOwnersStatement = dbConnection.prepareStatement(deleteOwnersQuery);
            deleteOwnersStatement.executeUpdate();

            PreparedStatement deleteDogsStatement = dbConnection.prepareStatement(deleteDogsQuery);
            deleteDogsStatement.executeUpdate();

            //init db
            PreparedStatement initOwnersStatement = dbConnection.prepareStatement(initOwnersQuery);
            initOwnersStatement.executeUpdate();

            PreparedStatement initDogsStatement = dbConnection.prepareStatement(initDogsQuery);
            initDogsStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/


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