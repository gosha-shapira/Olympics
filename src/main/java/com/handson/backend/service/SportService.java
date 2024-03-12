package com.handson.backend.service;

import com.handson.backend.enums.IntensityEnum;
import com.handson.backend.model.Sport;
import com.handson.backend.repo.SportRepo;
import org.springframework.stereotype.Service;

@Service
public class SportService {

    //region Members
    private final SportRepo repo;
    //endregion


    //region Constructors
    public SportService(SportRepo repo) {
        this.repo = repo;
    }
    //endregion


    //region Public Methods
    public Iterable<Sport> all() {
        return repo.findAll();
    }

    public Sport save(Sport sport) {
        return repo.save(sport);
    }

    public void delete(Sport sport) {
        repo.delete(sport);
    }

    public Sport findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Iterable<Sport> findByName(String name) {
        return repo.findAllByNameContaining(name);
    }

    public Iterable<Sport> findByIntensity(String intensity) {
        return repo.findAllByIntensity(IntensityEnum.valueOf(intensity));
    }

    public Iterable<Sport> findBySportTeam(String sportTeam) {
        return repo.findAllBySportTeam(sportTeam);
    }
    //endregion
}
