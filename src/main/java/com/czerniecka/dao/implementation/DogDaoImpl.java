package com.czerniecka.dao.implementation;

import com.czerniecka.dao.DogDao;
import com.czerniecka.dao.OwnerDao;
import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;

import java.sql.*;


public class DogDaoImpl implements DogDao {

    private static final String insertDog = "" +
            "INSERT INTO JDBC_HOMEWORK.DOG (NAME, BREED, OWNER_ID)           \n" +
            "VALUES (?, ?, ?)                                               ";

    private static final String updateDog = "" +
            " UPDATE DOG                                    \n" +
            " SET NAME = ?, BREED = ?, OWNER_ID = ?         \n" +
            " WHERE ID =?                                     " ;

    private Connection dbConnection;

    public DogDaoImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean addDog(Dog dog, Owner owner) {
        boolean result = false;

        if(owner != null) {
                try {
                    if(dog.getId()==null){
                    PreparedStatement insertDogStatement = dbConnection.prepareStatement(insertDog, Statement.RETURN_GENERATED_KEYS);

                    insertDogStatement.setString(1, dog.getDogName());
                    insertDogStatement.setString(2, dog.getBreed());
                    insertDogStatement.setString(3, owner.getName());

                    result = insertDogStatement.executeUpdate() > 0;

                    ResultSet generatedKeys = insertDogStatement.getGeneratedKeys();
                    generatedKeys.next();
                    dog.setId(generatedKeys.getLong(1));

                    }else {

                        PreparedStatement updateDogStatement = dbConnection.prepareStatement(updateDog, Statement.RETURN_GENERATED_KEYS);

                        updateDogStatement.setString(1, dog.getDogName());
                        updateDogStatement.setString(2, dog.getBreed());
                        updateDogStatement.setLong(3, owner.getId());
                        updateDogStatement.setLong(4, dog.getId());

                        result = updateDogStatement.executeUpdate() == 0;
                    }

                }catch (SQLException e) {
                    e.printStackTrace();
                }

        }

        return result;
    }

    @Override
    public int deleteDog(Long dogId) {

        int numberOfDogsDeleted = 0;

        String deleteDogQuery = "" +
                " DELETE        \n" +
                " FROM JDBC_HOMEWORK.DOG      \n" +
                " WHERE ID = ?     " ;

        try {
            PreparedStatement deleteStatement = dbConnection.prepareStatement(deleteDogQuery);

            deleteStatement.setLong(1, dogId);
            numberOfDogsDeleted = deleteStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfDogsDeleted;
    }

    @Override
    public Dog findDogByOwnerId(Long ownerId) {

        Dog dogToFind = new Dog();

        String query = "" +
                " SELECT ID, NAME, BREED          \n" +
                " FROM JDBC_HOMEWORK.DOG                        \n" +
                " WHERE OWNER_ID = ?          " ;

        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setLong(1, ownerId);

            ResultSet cursor = preparedStatement.executeQuery();

            while(cursor.next()){
                dogToFind = new Dog(
                        cursor.getLong(1),
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
