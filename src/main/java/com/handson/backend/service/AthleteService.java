package com.handson.backend.service;

import com.handson.backend.model.Athlete;
import com.handson.backend.repo.AthleteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    AthleteRepo repo;

    public Iterable<Athlete> all() {
        return repo.findAll();
    }

    public Optional<Athlete> findById(Long id) {
        return repo.findById(id);
    }

    public Athlete save(Athlete student) {
        return repo.save(student);
    }

    public void delete(Athlete student) {
        repo.delete(student);
    }

    public List<Athlete> getAthletesWithAgeHigherThan (Integer age) {
        return repo.findAllByAgeGreaterThan(age);
    }

    public List<Athlete> getAthletesWithAgeLessThan (Integer age) {
        return repo.findAllByAgeLessThan(age);
    }
}
