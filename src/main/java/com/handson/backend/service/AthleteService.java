package com.handson.backend.service;

import com.handson.backend.model.Athlete;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.model.dto.AthleteIn;
import com.handson.backend.repo.AthleteRepo;
import com.handson.backend.repo.SportsTeamRepo;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Athlete athlete = athleteIn.toAthlete(sportsTeamRepo.findById(athleteIn.getTeamId()).orElse(null));

        if (athleteIn.getTeamId() != null) {
            SportsTeam team = sportsTeamRepo.findById(athleteIn.getTeamId()).orElse(null);
            athlete.setTeam(team);
        }

        return repo.save(athlete);
    }

    public Athlete updateAthlete(Athlete athlete, AthleteIn athleteIn) {
        athleteIn.updateAthlete(athlete, sportsTeamRepo);

        if (athleteIn.getTeamId() != null) {
            SportsTeam team = sportsTeamRepo.findById(athleteIn.getTeamId()).orElse(null);
            athlete.setTeam(team);
        }
        return athlete;
    }
    //endregion

    //region Private methods
    public void validateAthleteIn(AthleteIn athleteIn) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<AthleteIn>> violations = validator.validate(athleteIn);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<AthleteIn> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException("Invalid AthleteIn: " + sb.toString());
        }

        if (athleteIn.getTeamId() != null) {
            if (!sportsTeamRepo.existsById(athleteIn.getTeamId())) {
                throw new IllegalArgumentException("Team with id: " + athleteIn.getTeamId() + " not found");
            }
        }
    }
    //endregion
}
