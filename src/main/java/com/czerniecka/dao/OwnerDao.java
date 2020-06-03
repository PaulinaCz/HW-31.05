package com.czerniecka.dao;

import com.czerniecka.model.Dog;
import com.czerniecka.model.Owner;

import java.util.List;

public interface OwnerDao {

    boolean addOwner(Owner owner, Dog dog);

    int deleteOwner(int ownerId);

    int updateOwner(int ownerId);

    List<Owner> getAllOwners();

}
