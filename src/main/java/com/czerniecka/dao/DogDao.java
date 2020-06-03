package com.czerniecka.dao;

import com.czerniecka.model.Dog;

public interface DogDao {

    boolean addDog(Dog dog);

    int deleteDog(int dogId);

    int updateDog(int dogId);

    Dog findDogByOwnerId(int ownerId);

}
