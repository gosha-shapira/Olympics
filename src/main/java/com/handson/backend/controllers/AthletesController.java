package com.handson.backend.controllers;

import com.handson.backend.model.Athlete;
import com.handson.backend.model.AthleteIn;
import com.handson.backend.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/athletes")
public class AthletesController {

    @Autowired
    AthleteService athleteService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAthletes() {
        return new ResponseEntity<>(athleteService.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneAthlete(@PathVariable Long id) {
        return new ResponseEntity<>(athleteService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createAthlete(@RequestBody @Valid AthleteIn athleteIn) {
        Athlete athlete = athleteIn.toAthlete();
        athlete = athleteService.save(athlete);
        return new ResponseEntity<>(athlete, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAthlete(@PathVariable Long id, @RequestBody @Valid AthleteIn athlete) {
        Optional<Athlete> dbAthlete = athleteService.findById(id);
        if (dbAthlete.isEmpty()) {
            throw new RuntimeException("Athlete with id: " + id + " not found");
        }
        athlete.updateAthlete(dbAthlete.get());
        Athlete updatedAthlete = athleteService.save(dbAthlete.get());
        return new ResponseEntity<>(updatedAthlete, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAthlete(@PathVariable Long id) {
        Optional<Athlete> dbAthlete = athleteService.findById(id);
        if (dbAthlete.isEmpty()) {
            throw new RuntimeException("Athlete with id: " + id + " not found");
        }
        athleteService.delete(dbAthlete.get());
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @RequestMapping(value = "/ageGreater", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentWithSatHigherThan(@RequestParam Integer age)
    {
        return new ResponseEntity<>(athleteService.getAthletesWithAgeHigherThan(age), HttpStatus.OK);
    }

    @RequestMapping(value = "/ageLower", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentWithSatLessThan(@RequestParam Integer age)
    {
        return new ResponseEntity<>(athleteService.getAthletesWithAgeLessThan(age), HttpStatus.OK);
    }
}