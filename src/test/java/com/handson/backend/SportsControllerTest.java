package com.handson.backend;

import com.handson.backend.controllers.SportsController;
import com.handson.backend.enums.IntensityEnum;
import com.handson.backend.model.Sport;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.model.dto.SportIn;
import com.handson.backend.service.SportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SportsControllerTest {

    @InjectMocks
    private SportsController sportsController;

    @Mock
    private SportService sportService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllSports_returnsListOfSports() {
        Sport sport1 = createSport();
        Sport sport2 = createSport();
        List<Sport> sports = Arrays.asList(sport1, sport2);

        when(sportService.all()).thenReturn(sports);

        ResponseEntity<?> response = sportsController.getAllSports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sports, response.getBody());
    }

    @Test
    public void getOneSport_returnsSport() {
        Sport sport = new Sport();
        Long id = 1L;

        when(sportService.findById(id)).thenReturn(sport);

        ResponseEntity<?> response = sportsController.getOneSport(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sport, response.getBody());
    }

    @Test
    public void getOneSport_returnsNotFound_whenSportDoesNotExist() {
        Long id = 1L;

        when(sportService.findById(id)).thenReturn(null);

        ResponseEntity<?> response = sportsController.getOneSport(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createSport_returnsCreatedSport() {
        Sport sport = new Sport();

        when(sportService.save(any(Sport.class))).thenReturn(sport);

        ResponseEntity<?> response = sportsController.createSport(new SportIn());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sport, response.getBody());
    }

    @Test
    public void updateSport_returnsUpdatedSport() {
        Sport sport = new Sport();
        Long id = 1L;

        when(sportService.findById(id)).thenReturn(sport);
        when(sportService.save(any(Sport.class))).thenReturn(sport);

        ResponseEntity<?> response = sportsController.updateSport(id, new SportIn());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sport, response.getBody());
    }

    @Test
    public void deleteSport_returnsOk_whenSportExists() {
        Sport sport = new Sport();
        Long id = 1L;

        when(sportService.findById(id)).thenReturn(sport);

        ResponseEntity<?> response = sportsController.deleteSport(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteSport_returnsNotFound_whenSportDoesNotExist() {
        Long id = 1L;

        when(sportService.findById(id)).thenReturn(null);

        ResponseEntity<?> response = sportsController.deleteSport(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private Sport createSport() {
        Sport sport = new Sport();
        sport.setName("Sport");
        sport.setDescription("Description");
        sport.setRules("Rules");
        sport.setEquipment("Equipment");
        sport.setPopularity("Popularity");
        sport.setSportIntensity(IntensityEnum.HIGH);
        sport.setSportsTeam(Arrays.asList(createSportsTeam()));
        sport.setSportOlympic(true);
        sport.setSportParalympic(false);
        sport.setSportWorldRecord("World Record");

        return sport;
    }

    private SportsTeam createSportsTeam() {
        SportsTeam sportsTeam = new SportsTeam();
        sportsTeam.setName("Team");
        sportsTeam.setCountry("Country");

        Sport sport = new Sport();
        sport.setName("Sport");
        sport.setDescription("Description");
        sport.setRules("Rules");
        sport.setEquipment("Equipment");
        sport.setPopularity("Popularity");
        sport.setSportIntensity(IntensityEnum.HIGH);
        sport.setSportsTeam(Arrays.asList(sportsTeam));
        sport.setSportOlympic(true);
        sport.setSportParalympic(false);
        sport.setSportWorldRecord("World Record");

        sportsTeam.setSport(sport);

        return sportsTeam;

    }
}