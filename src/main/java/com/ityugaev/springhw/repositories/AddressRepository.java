package com.ityugaev.springhw.repositories;

import com.ityugaev.springhw.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}