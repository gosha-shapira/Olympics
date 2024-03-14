package com.handson.backend.repo;

import com.handson.backend.enums.IntensityEnum;
import com.handson.backend.model.Sport;
import org.springframework.data.repository.CrudRepository;

public interface SportRepo extends CrudRepository<Sport, Long> {

    // get all sports with a name containing the given string
    Iterable<Sport> findAllByNameContaining(String name);

    // get all sports in which the given sportTeam is present
    Iterable<Sport> findAllBySportsTeam(String sportsTeam);

}
