package com.handson.backend.service;

import com.handson.backend.model.Athlete;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.model.dto.AthleteIn;
import com.handson.backend.repo.AthleteRepo;
import com.handson.backend.repo.SportsTeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    //region Members
    private final AthleteRepo repo;
    private final SportsTeamRepo sportsTeamRepo;

    //endregion

    //region Constructors
    public AthleteService(AthleteRepo repo, SportsTeamRepo sportsTeamRepo) {
        this.repo = repo;
        this.sportsTeamRepo = sportsTeamRepo;
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

    public Athlete createAthlete(AthleteIn athleteIn) {
        Athlete athlete = athleteIn.toAthlete();

        if (athleteIn.getTeamId() != null) {
            SportsTeam team = sportsTeamRepo.findById(athleteIn.getTeamId()).orElse(null);
            athlete.setTeam(team);
        }

        return repo.save(athlete);
    }

    public Athlete updateAthlete(Athlete athlete, AthleteIn athleteIn) {
        athleteIn.updateAthlete(athlete);

        if (athleteIn.getTeamId() != null) {
            SportsTeam team = sportsTeamRepo.findById(athleteIn.getTeamId()).orElse(null);
            athlete.setTeam(team);
        }
        return athlete;
    }
    //endregion
}
