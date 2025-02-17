package com.handson.backend.service;

import com.handson.backend.model.Sport;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.repo.SportsTeamRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SportsTeamService {

    // region Members
    private final SportsTeamRepo repo;
    // endregion

    // region Constructors
    public SportsTeamService(SportsTeamRepo repo) {
        this.repo = repo;
    }
    // endregion

    // region Public Methods
    public Iterable<SportsTeam> all() {
        return repo.findAll();
    }

    public SportsTeam save(SportsTeam sportsTeam) {
        return repo.save(sportsTeam);
    }

    public void delete(SportsTeam sportsTeam) {
        repo.delete(sportsTeam);
    }

    public Optional<SportsTeam> findById(Long id) {
        return repo.findById(id);
    }

    public Iterable<SportsTeam> findByName(String name) {
        return repo.findAllByNameContaining(name);
    }

    public Iterable<SportsTeam> findBySport(Sport sport) {
        return repo.findAllBySport(sport);
    }

    public Iterable<SportsTeam> findByCountry(String country) {
        return repo.findAllByCountry(country);
    }

    public Iterable<SportsTeam> findByCity(String city) {
        return repo.findAllByCity(city);
    }

    public Iterable<SportsTeam> findByLeague(String league) {
        return repo.findAllByLeague(league);
    }

    // update sports team
    public SportsTeam updateSportsTeam(SportsTeam sportsTeam) {
        Optional<SportsTeam> sportsTeamOptional = repo.findById(sportsTeam.getId());
        if (sportsTeamOptional.isPresent()) {
            SportsTeam sportsTeamToUpdate = sportsTeamOptional.get();
            sportsTeamToUpdate.updateSportsTeam(sportsTeam);
        }

        return sportsTeam;

    }
}
