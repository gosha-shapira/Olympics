package com.handson.backend.model.dto;


import com.handson.backend.model.Athlete;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.repo.SportsTeamRepo;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Optional;

@Data
public class AthleteIn implements Serializable {

    //region Members
    @NotEmpty
    @Length(max = 60)
    private String fullName;

    @NotEmpty
    @Length(max = 60)
    private String mainSport;

    @Min(16)
    @Max(65)
    private Integer age;

    @Length(max = 60)
    private String optionalSport;

    @Length(max = 60)
    private String nationality;

    @Length(max = 500)
    private String profilePicture;

    private Long teamId;
    //endregion


    //region Public methods
    public Athlete toAthlete(SportsTeam team) {
        return Athlete.builder()
                .fullName(fullName)
                .mainSport(mainSport)
                .age(age)
                .optionalSport(optionalSport).build();

    }

    public Athlete toAthlete(SportsTeamRepo sportsTeamRepo) {
        Optional<SportsTeam> team = sportsTeamRepo.findById(teamId);
        return Athlete.builder()
                .fullName(fullName)
                .mainSport(mainSport)
                .age(age)
                .optionalSport(optionalSport).build();
    }

    public void updateAthlete(Athlete athlete, SportsTeamRepo sportsTeamRepo) {
        Optional<SportsTeam> team = sportsTeamRepo.findById(teamId);
        athlete.setFullName(fullName);
        athlete.setMainSport(mainSport);
        athlete.setAge(age);
        athlete.setOptionalSport(optionalSport);
        athlete.setTeam(team.orElse(null));
        //endregion
    }
}