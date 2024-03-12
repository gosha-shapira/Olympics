package com.handson.backend.model.dto;


import com.handson.backend.model.Athlete;
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

    @Min(16)
    @Max(65)
    private Integer age;

    @Length(max = 60)
    private String optionalSport;

    @Length(max = 60)
    private String nationality;

    @Length(max = 500)
    private String profilePicture;
    //endregion


    //region Public methods
    public Athlete toAthlete() {
        return Athlete.builder().createdAt(new Date()).fullName(fullName)
                .mainSport(mainSport).age(age).optionalSport(optionalSport).nationality(nationality)
                .profilePicture(profilePicture).build();
    }

    public void updateAthlete(Athlete athlete) {
        athlete.setFullName(fullName);
        athlete.setMainSport(mainSport);
        athlete.setAge(age);
        athlete.setOptionalSport(optionalSport);
        athlete.setNationality(nationality);
        athlete.setProfilePicture(profilePicture);
    }
    //endregion

}