package com.handson.backend.repo;

import com.handson.backend.model.Athlete;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AthleteRepo extends CrudRepository<Athlete,Long> {
    List<Athlete> findAllByAgeGreaterThan(Integer age);
    List<Athlete> findAllByAgeLessThan(Integer age);
}
