package com.handson.backend;

import com.handson.backend.controllers.AthletesController;
import com.handson.backend.model.Athlete;
import com.handson.backend.model.AthleteIn;
import com.handson.backend.service.AthleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AthletesControllerTest {

    @InjectMocks
    AthletesController athletesController;

    @Mock
    AthleteService athleteService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getOneAthleteReturnsAthleteWhenExists() {
        Athlete athlete = new Athlete();
        athlete.setId(1L);
        when(athleteService.findById(1L)).thenReturn(Optional.of(athlete));

        ResponseEntity<?> response = athletesController.getOneAthlete(1L);
        Athlete actualAthlete = ((Optional<Athlete>) response.getBody()).get();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(athlete, actualAthlete);
    }

    @Test
    public void getOneAthleteReturnsNotFoundWhenDoesNotExist() {
        when(athleteService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = athletesController.getOneAthlete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createAthleteReturnsCreatedAthlete() {
        AthleteIn athleteIn = createAthleteIn();
        Athlete athlete = athleteIn.toAthlete();
        when(athleteService.save(any())).thenReturn(athlete);

        ResponseEntity<?> response = athletesController.createAthlete(athleteIn);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(athlete, response.getBody());
    }

    private AthleteIn createAthleteIn() {
        AthleteIn athleteIn = new AthleteIn();
        athleteIn.setFullName("John Doe");
        athleteIn.setMainSport("Soccer");
        athleteIn.setAge(25);
        athleteIn.setOptionalSport("Basketball");
        athleteIn.setNationality("USA");
        athleteIn.setProfilePicture("https://www.google.com");
        return athleteIn;
    }
}