package com.handson.backend.controllers;

import com.handson.backend.model.Athlete;
import com.handson.backend.model.dto.AthleteIn;
import com.handson.backend.service.AthleteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Api
@RequestMapping("/api/athletes")
public class AthletesController {

    //region Properties
    private final AthleteService athleteService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AthletesController.class);
    //endregion

    //region Constructors
    @Autowired
    public AthletesController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }
    //endregion

    //region Public Methods
    @ApiOperation(value = "Get all athletes", notes = "Get all athletes")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAthletes() {
        LOGGER.info("Getting all athletes");
        return new ResponseEntity<>(athleteService.all(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Athlete.class, responseContainer = "List") })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneAthlete(@PathVariable Long id) {
        LOGGER.info("Getting athlete with id: " + id);
        Optional<Athlete> retVal =  athleteService.findById(id);
        if (retVal.isEmpty()) {
            LOGGER.error("Athlete with id: " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(athleteService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createAthlete(@RequestBody @Valid AthleteIn athleteIn) {
        athleteService.validateAthleteIn(athleteIn);
        LOGGER.info("Creating new athlete");
        Athlete athlete = athleteService.createAthlete(athleteIn);
        LOGGER.info("Athlete created: " + athlete);

        return new ResponseEntity<>(athlete, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> updateAthlete(@PathVariable Long id, @RequestBody @Valid AthleteIn athleteIn) {
//        Optional<Athlete> dbAthlete = athleteService.findById(id);
//        if (dbAthlete.isEmpty()) {
//            LOGGER.error("Athlete with id: " + id + " not found");
//            throw new RuntimeException("Athlete with id: " + id + " not found");
//        }
//        Athlete updatedAthlete = athleteService.updateAthlete(dbAthlete.get(), athleteIn);
//        LOGGER.info("Athlete updated: " + updatedAthlete);
//        return new ResponseEntity<>(updatedAthlete, HttpStatus.OK);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAthlete(@PathVariable Long id) {
        Optional<Athlete> dbAthlete = athleteService.findById(id);
        if (dbAthlete.isEmpty()) {
            LOGGER.error("Athlete with id: " + id + " not found");
            throw new RuntimeException("Athlete with id: " + id + " not found");
        }
        athleteService.delete(dbAthlete.get());
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @RequestMapping(value = "/ageGreater", method = RequestMethod.GET)
    public ResponseEntity<?> getAthletesWithAgeHigherThan(@RequestParam Integer age)
    {
        return new ResponseEntity<>(athleteService.getAthletesWithAgeHigherThan(age), HttpStatus.OK);
    }

    @RequestMapping(value = "/ageLower", method = RequestMethod.GET)
    public ResponseEntity<?> getAthletesWithAgeLessThan(@RequestParam Integer age)
    {
        return new ResponseEntity<>(athleteService.getAthletesWithAgeLessThan(age), HttpStatus.OK);
    }
    //endregion
}