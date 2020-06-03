package com.czerniecka.dao;

import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;

public interface DogDao {

    boolean addDog(Dog dog, Owner owner);

    int deleteDog(int dogId);

    int updateDog(int dogId);

    Dog findDogByOwnerId(int ownerId);

}
