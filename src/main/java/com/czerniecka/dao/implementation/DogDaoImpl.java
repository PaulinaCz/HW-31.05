package com.czerniecka.dao.implementation;

import com.czerniecka.dao.DogDao;
import com.czerniecka.model.Dog;

public class DogDaoImpl implements DogDao {
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
        return null;
    }
}
