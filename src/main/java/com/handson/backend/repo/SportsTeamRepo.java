package com.handson.backend.repo;

import com.handson.backend.model.Athlete;
import com.handson.backend.model.Sport;
import com.handson.backend.model.SportsTeam;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SportsTeamRepo extends CrudRepository<SportsTeam, Long> {

    Optional<SportsTeam> findById(Long id);
    Iterable<SportsTeam> findAllByNameContaining(String name);
    Iterable<SportsTeam> findAllByCountry(String country);
    Iterable<SportsTeam> findAllByCity(String city);
    Iterable<SportsTeam> findAllByLeague(String league);
    Iterable<SportsTeam> findAllByStadium(String stadium);
    Iterable<SportsTeam> findAllByCoach(String coach);
    Iterable<SportsTeam> findAllByFoundation(String foundation);
    Iterable<SportsTeam> findAllBySport(Sport sport);
    Iterable<SportsTeam> findAllByAthletes(Athlete athlete);
}
