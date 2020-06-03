package com.czerniecka.dao.implementation;

import com.czerniecka.dao.DogDao;
import com.czerniecka.model.Dog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DogDaoImpl implements DogDao {

    private Connection dbConnection;
    private OwnerDaoImpl ownerDao;

    public DogDaoImpl(Connection dbConnection, OwnerDaoImpl ownerDao) {
        this.dbConnection = dbConnection;
        this.ownerDao = ownerDao;
    }

    @Override
    public boolean addDog(Dog dog) {
        return false;
    }

    @Override
    public int deleteDog(int dogId) {
        return 0;
    }

    @Override
    public int updateDog(int dogId) {
        return 0;
    }

    @Override
    public Dog findDogByOwnerId(int ownerId) {

        Dog dogToFind = new Dog();

        String query = "" +
                "SELECT * FROM DOG           " +
                "WHERe OWNER_ID = ?            " ;

        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setInt(1, ownerId);

            ResultSet cursor = preparedStatement.executeQuery();

            while(cursor.next()){
                dogToFind = new Dog(
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dogToFind;
    }
}
