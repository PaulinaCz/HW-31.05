package com.czerniecka.dao;

import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;


public interface DogDao {

    boolean addDog(Dog dog, Owner owner);

    int deleteDog(Long dogId);

    Dog findDogByOwnerId(Long ownerId);

}
