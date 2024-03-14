package com.handson.backend.controllers;


import com.handson.backend.service.SportsTeamService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
