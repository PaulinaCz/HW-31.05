package com.czerniecka.dao.implementation;

import com.czerniecka.dao.DogDao;
import com.czerniecka.dao.OwnerDao;
import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDaoImpl  implements OwnerDao {

    private Connection dbConnection;
    private DogDao dogDao;

    public OwnerDaoImpl(Connection dbConnection, DogDao dogDao) {
        this.dbConnection = dbConnection;
        this.dogDao = dogDao;
    }

    @Override
    public boolean addOwner(Owner owner, Dog dog) {

            String insert = "" +
                    " INSERT INTO JDBC_HOMEWORK.OWNER (NAME, SEX, CITY, STREET, POSTCODE)        \n" +
                    " VALUES (?, ?, ?, ?, ?)                                         " ;

            boolean result = false;
            try{
                PreparedStatement insertStatement = dbConnection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setString(1, owner.getName());
                insertStatement.setString(2, owner.getSex());
                insertStatement.setString(3, owner.getCity());
                insertStatement.setString(4, owner.getStreet());
                insertStatement.setString(5, owner.getPostCode());

               if(insertStatement.executeUpdate() > 0){
                    dogDao.addDog(dog, owner);
                }

                result = insertStatement.executeUpdate() > 0;


                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if(generatedKeys.next()){
                    owner.setId(generatedKeys.getInt(1));
                }



            } catch (SQLException e) {
                e.printStackTrace();
            }

        return result;
    }

    @Override
    public int deleteOwner(int ownerId) {

        int numberOfDeletedOwners = 0;

        String deleteQuery = "" +
                " DELETE            \n" +
                " FROM JDBC_HOMEWORK.OWNER        \n" +
                " WHERE ID = ?        " ;

        try {
            PreparedStatement deleteOwnerStatement = dbConnection.prepareStatement(deleteQuery);
            deleteOwnerStatement.setInt(1, ownerId);
            dogDao.deleteDog(dogDao.findDogByOwnerId(ownerId).getId());
            numberOfDeletedOwners = deleteOwnerStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return numberOfDeletedOwners;
    }

    @Override
    public int updateOwner(int ownerId) {
        return 0;
    }

    @Override
    public List<Owner> getAllOwners() {

        List<Owner> owners = new ArrayList<>();

        String getAllQuery = "" +
                " SELECT ID, NAME, SEX, CITY, STREET, POSTCODE         \n" +
                " FROM JDBC_HOMEWORK.OWNER                                            " ;

        try {
            Statement statement = dbConnection.createStatement();
            int readId = Owner.ID_OF_NOT_PERSISTENT_PERSON; //1
            String readName;        //2
            String readSex;         //3
            String readCity;        //4
            String readStreet;      //5
            String readPostcode;    //6
            Owner readOwner;

            ResultSet resultFromDb = statement.executeQuery(getAllQuery);
            while(resultFromDb.next()){
                readId = resultFromDb.getInt(1);
                readName = resultFromDb.getString(2);
                readSex = resultFromDb.getString(3);
                readCity = resultFromDb.getString(4);
                readStreet = resultFromDb.getString(5);
                readPostcode = resultFromDb.getString(6);

                readOwner = new Owner(readId, readName, readSex, readCity, readStreet, readPostcode
                , dogDao.findDogByOwnerId(readId));

                owners.add(readOwner);

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owners;
    }
}
