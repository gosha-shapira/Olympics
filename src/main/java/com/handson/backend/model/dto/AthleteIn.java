package com.handson.backend.model.dto;


import com.handson.backend.model.Athlete;
import com.handson.backend.model.SportsTeam;
import com.handson.backend.repo.SportRepo;
import com.handson.backend.repo.SportsTeamRepo;
import com.handson.backend.service.SportService;
import com.handson.backend.service.SportsTeamService;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
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
    public Athlete toAthlete(SportsTeam sportsTeam) {
        Athlete athlete = Athlete.builder()
                .createdAt(new Date())
                .fullName(fullName)
                .mainSport(mainSport)
                .age(age)
                .optionalSport(optionalSport)
                .nationality(nationality)
                .profilePicture(profilePicture)
                .team(sportsTeam)
                .build();

        return athlete;
    }

    public void updateAthlete(Athlete athlete) {
        athlete.setFullName(fullName);
        athlete.setMainSport(mainSport);
        athlete.setAge(age);
        athlete.setOptionalSport(optionalSport);
        athlete.setNationality(nationality);
        athlete.setProfilePicture(profilePicture);
        athlete.setTeam(athlete.getTeam());
    }
    //endregion

}