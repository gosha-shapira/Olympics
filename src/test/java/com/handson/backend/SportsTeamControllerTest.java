package com.handson.backend;

import com.handson.backend.controllers.SportsTeamController;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.service.SportsTeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class SportsTeamControllerTest {

    @Mock
    private SportsTeamService sportsTeamService;

    private SportsTeamController sportsTeamController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        sportsTeamController = new SportsTeamController(sportsTeamService);
    }

    @Test
    @DisplayName("getAllSportsTeams returns all sports teams")
    public void getAllSportsTeamsReturnsAllSportsTeams() {
        when(sportsTeamService.all()).thenReturn(List.of(new SportsTeam(), new SportsTeam()));
        ResponseEntity<?> response = sportsTeamController.getAllSportsTeams();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("getOneSportsTeam returns sports team if exists")
    public void getOneSportsTeamReturnsSportsTeamIfExists() {
        when(sportsTeamService.findById(1L)).thenReturn(Optional.of(new SportsTeam()));
        ResponseEntity<?> response = sportsTeamController.getOneSportsTeam(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("getOneSportsTeam returns not found if sports team does not exist")
    public void getOneSportsTeamReturnsNotFoundIfSportsTeamDoesNotExist() {
        when(sportsTeamService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<?> response = sportsTeamController.getOneSportsTeam(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("createSportsTeam creates a new sports team")
    public void createSportsTeamCreatesANewSportsTeam() {
        SportsTeam sportsTeam = new SportsTeam();
        when(sportsTeamService.save(sportsTeam)).thenReturn(sportsTeam);
        ResponseEntity<?> response = sportsTeamController.createSportsTeam(sportsTeam);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("deleteSportsTeam deletes sports team if exists")
    public void deleteSportsTeamDeletesSportsTeamIfExists() {
        SportsTeam sportsTeam = new SportsTeam();
        when(sportsTeamService.findById(1L)).thenReturn(Optional.of(sportsTeam));
        ResponseEntity<?> response = sportsTeamController.deleteSportsTeam(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("deleteSportsTeam returns not found if sports team does not exist")
    public void deleteSportsTeamReturnsNotFoundIfSportsTeamDoesNotExist() {
        when(sportsTeamService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<?> response = sportsTeamController.deleteSportsTeam(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("updateSportsTeam updates sports team if exists")
    public void updateSportsTeamUpdatesSportsTeamIfExists() {
        SportsTeam sportsTeam = new SportsTeam();
        when(sportsTeamService.findById(1L)).thenReturn(Optional.of(sportsTeam));
        when(sportsTeamService.save(sportsTeam)).thenReturn(sportsTeam);
        ResponseEntity<?> response = sportsTeamController.updateSportsTeam(1L, sportsTeam);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("updateSportsTeam throws exception if sports team does not exist")
    public void updateSportsTeamThrowsExceptionIfSportsTeamDoesNotExist() {
        SportsTeam sportsTeam = new SportsTeam();
        when(sportsTeamService.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> sportsTeamController.updateSportsTeam(1L, sportsTeam));
    }
}