package com.handson.backend.service;

import com.handson.backend.model.Athlete;
import com.handson.backend.repo.AthleteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    //region Members
    private final AthleteRepo repo;
    //endregion

    //region Constructors
    public AthleteService(AthleteRepo repo) {
        this.repo = repo;
    }
    //endregion

    //region Public Methods
    public Iterable<Athlete> all() {
        return repo.findAll();
    }

    public Optional<Athlete> findById(Long id) {
        return repo.findById(id);
    }

    public Athlete save(Athlete athlete) {
        return repo.save(athlete);
    }

    public void delete(Athlete athlete) {
        repo.delete(athlete);
    }

    public List<Athlete> getAthletesWithAgeHigherThan(Integer age) {
        return repo.findAllByAgeGreaterThan(age);
    }

    public List<Athlete> getAthletesWithAgeLessThan(Integer age) {
        return repo.findAllByAgeLessThan(age);
    }
    //endregion
}
