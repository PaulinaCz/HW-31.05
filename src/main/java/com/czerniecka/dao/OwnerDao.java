package com.czerniecka.dao;

import com.czerniecka.model.Owner;

import java.util.List;

public interface OwnerDao {

    boolean addOwner(Owner owner);

    int deleteOwner(Long ownerId);

    List<Owner> getAllOwners();

}
