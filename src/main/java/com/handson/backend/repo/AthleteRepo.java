package com.handson.backend.repo;

import com.handson.backend.model.Athlete;
import org.springframework.data.repository.CrudRepository;

public interface AthleteRepo extends CrudRepository<Athlete,Long> {
}
