package com.czerniecka.dao.implementation;

import com.czerniecka.dao.DogDao;
import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;

import java.sql.*;


public class DogDaoImpl implements DogDao {

    private Connection dbConnection;

    public DogDaoImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean addDog(Dog dog, Owner owner) {
        boolean result = false;

        String insertDog = "" +
                "INSERT INTO JDBC_HOMEWORK.DOG (NAME, BREED, OWNER_ID)           \n" +
                "VALUES ( ?, ?, ? )                                 " ;

        try {
            PreparedStatement insertDogStatement = dbConnection.prepareStatement(insertDog);

            insertDogStatement.setString(1, dog.getDogName());
            insertDogStatement.setString(2, dog.getDogName());
            insertDogStatement.setInt(3, owner.getId());

            result = insertDogStatement.executeUpdate() > 0;

            ResultSet generatedKeys = insertDogStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                dog.setId(generatedKeys.getInt(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteDog(int dogId) {

        int numberOfDogsDeleted = 0;

        String deleteDogQuery = "" +
                " DELETE        \n" +
                " FROM JDBC_HOMEWORK.DOG      \n" +
                " WHERE ID = ?     " ;

        try {
            PreparedStatement deleteStatement = dbConnection.prepareStatement(deleteDogQuery);

            deleteStatement.setInt(1, dogId);
            numberOfDogsDeleted = deleteStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return numberOfDogsDeleted;
    }

    @Override
    public int updateDog(int dogId) {
        return 0;
    }

    @Override
    public Dog findDogByOwnerId(int ownerId) {

        Dog dogToFind = new Dog();

        String query = "" +
                " SELECT ID, NAME, BREED          \n" +
                " FROM JDBC_HOMEWORK.DOG                        \n" +
                " WHERE OWNER_ID = ?          " ;

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
