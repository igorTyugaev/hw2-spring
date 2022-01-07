package com.ityugaev.springhw.repositories;

import com.ityugaev.springhw.models.Child;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<Child, Integer> {
}
