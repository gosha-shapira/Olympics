package com.handson.backend.controllers;

import com.handson.backend.enums.IntensityEnum;
import com.handson.backend.model.Sport;
import com.handson.backend.model.dto.SportIn;
import com.handson.backend.service.SportService;
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

@RestController
@Api
@RequestMapping("/api/sports")
public class SportsController {

    //region Properties
    private final SportService sportService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SportsController.class);
    //endregion

    //region Constructors
    @Autowired
    public SportsController(SportService sportService) {
        this.sportService = sportService;
    }
    //endregion

    //region Public Methods
    @ApiOperation(value = "Get all sports", notes = "Get all sports")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSports() {
        LOGGER.info("Getting all sports");
        return new ResponseEntity<>(sportService.all(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get one sport", notes = "Get one sport")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Sport.class, responseContainer = "List")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneSport(@PathVariable Long id) {
        LOGGER.info("Getting sport with id: " + id);
        return new ResponseEntity<>(sportService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> searchSportByName(@PathVariable String name) {
        LOGGER.info("Searching sport with name: " + name);
        return new ResponseEntity<>(sportService.findByName(name), HttpStatus.OK);
    }

//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public ResponseEntity<?> searchSportBySportTeam(@PathVariable String sportTeam) {
//        LOGGER.info("Searching sport with sportTeam: " + sportTeam);
//        return new ResponseEntity<>(sportService.findBySportsTeam(sportTeam), HttpStatus.OK);
//    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createSport(@RequestBody @Valid SportIn sportIn) {
        Sport sport = sportIn.toSport();

        LOGGER.info("Creating sport: " + sport);
        sport = sportService.save(sport);
        LOGGER.info("Sport created: " + sport);

        return new ResponseEntity<>(sport, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSport(@PathVariable Long id, @RequestBody @Valid SportIn sport) {
        Sport dbSport = sportService.findById(id);
        if (dbSport == null) {
            LOGGER.error("Sport with id: " + id + " not found");
            throw new RuntimeException("Sport with id: " + id + " not found");
        }
        sport.updateSport(dbSport);
        Sport updatedSport = sportService.save(dbSport);
        LOGGER.info("Sport updated: " + updatedSport);
        return new ResponseEntity<>(updatedSport, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSport(@PathVariable Long id) {
        Sport dbSport = sportService.findById(id);
        if (dbSport == null) {
            LOGGER.error("Sport with id: " + id + " not found");
            throw new RuntimeException("Sport with id: " + id + " not found");
        }
        sportService.delete(dbSport);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
