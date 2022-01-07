package com.ityugaev.springhw.repositories;

import com.ityugaev.springhw.models.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School, Integer> {
}
