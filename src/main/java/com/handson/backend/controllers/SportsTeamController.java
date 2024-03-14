package com.handson.backend.controllers;


import com.handson.backend.model.Sport;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.service.SportsTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@Api
@RequestMapping("/api/sportTeams")
public class SportsTeamController {

    //region Properties
    private final SportsTeamService sportTeamService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SportsController.class);

    public SportsTeamController(SportsTeamService sportTeamService) {
        this.sportTeamService = sportTeamService;
    }
    //endregion

    //region Public Methods

    @ApiOperation(value = "Get all sports teams", notes = "Get all sports teams")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSportsTeams() {
        LOGGER.info("Getting all sports teams");
        return new ResponseEntity<>(sportTeamService.all(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get one sports team", notes = "Get one sports team")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Sport.class, responseContainer = "List")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneSportsTeam(@PathVariable Long id) {
        Optional<SportsTeam> retVal = sportTeamService.findById(id);
        if (retVal.isEmpty()) {
            LOGGER.error("Sports team with id: " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Getting sports team with id: " + id);
        return new ResponseEntity<>(sportTeamService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createSportsTeam(@PathVariable SportsTeam sportsTeam) {
        LOGGER.info("Creating sports team with sportsTeam: " + sportsTeam);
        return new ResponseEntity<>(sportTeamService.save(sportsTeam), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSportsTeam(@PathVariable Long id) {
        Optional<SportsTeam> sportsTeam = sportTeamService.findById(id);
        if (sportsTeam.isEmpty()) {
            LOGGER.error("Sports team with id: " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Deleting sports team with id: " + id);
        sportTeamService.delete(sportsTeam.get());
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSportsTeam(@PathVariable Long id, @PathVariable SportsTeam sportsTeam) {
        Optional<SportsTeam> dbSportsTeam = sportTeamService.findById(id);
        if (dbSportsTeam.isEmpty()) {
            LOGGER.error("Sports team with id: " + id + " not found");
            throw new RuntimeException("Sports team with id: " + id + " not found");
        }
        sportTeamService.updateSportsTeam(sportsTeam);
        LOGGER.info("Updating sports team with id: " + id);
        return new ResponseEntity<>(sportTeamService.save(sportsTeam), HttpStatus.OK);
    }

    //endregion

}
