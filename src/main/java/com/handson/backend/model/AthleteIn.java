package com.handson.backend.model;


import com.handson.backend.repo.AthleteRepo;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
public class AthleteIn implements Serializable {

    //region Members
    @NotEmpty
    @Length(max = 60)
    private String fullName;

    @NotEmpty
    @Length(max = 60)
    private String mainSport;

    @Length(max = 60)
    private String optionalSport;

    @Length(max = 60)
    private String nationality;

    @Length(max = 500)
    private String profilePicture;
    //endregion


    //region Public methods
    public Athlete toAthlete() {
        return Athlete.builder().createdAt(new Date()).fullname(fullName)
                .mainSport(mainSport).optionalSport(optionalSport).nationality(nationality)
                .profilePicture(profilePicture).build();
    }

    public void updateAthlete(Athlete athlete) {
        athlete.setFullname(fullName);
        athlete.setMainSport(mainSport);
        athlete.setOptionalSport(optionalSport);
        athlete.setNationality(nationality);
        athlete.setProfilePicture(profilePicture);
    }
    //endregion

}