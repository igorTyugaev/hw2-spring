package com.ityugaev.springhw.repositories;

import com.ityugaev.springhw.models.Parent;
import org.springframework.data.repository.CrudRepository;

public interface ParentRepository extends CrudRepository<Parent, Integer> {
}
