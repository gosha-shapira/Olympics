package com.handson.backend.model.dto;


import java.io.Serializable;
import java.util.Date;

import com.handson.backend.enums.IntensityEnum;
import com.handson.backend.model.Sport;
import com.handson.backend.model.SportsTeam;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Data
public class SportIn implements Serializable {
    //region Members

    @NotEmpty
    @Length(max = 60)
    private String name;

    @NotEmpty
    @Length(max = 300)
    private String description;

    @NotEmpty
    @Length(max = 300) // should be an url to the rules of the sport
    private String rules;

    @Length(max = 300)
    private String equipment;

    @Length(max = 300)
    private String popularity;

    @Length(max = 12)
    private String sportGender;

    @Length(max = 10)
    private String sportGameTimeInMinutes;

    @Enumerated(EnumType.STRING)
    private IntensityEnum sportIntensity;

    @NotEmpty
    @Length(max = 60)
    private SportsTeam sportsTeam;

    private Boolean sportOlympic;

    private Boolean sportParalympic;

    @Length(max = 60)
    private String sportWorldRecord;
    //endregion


    public Sport toSport() {
        return Sport.builder().createdAt(new Date()).name(name)
                .description(description).rules(rules).equipment(equipment).popularity(popularity)
                .sportGender(sportGender).sportGameTimeInMinutes(sportGameTimeInMinutes).sportIntensity(sportIntensity)
                .sportsTeam(sportsTeam).sportOlympic(sportOlympic).sportParalympic(sportParalympic).sportWorldRecord(sportWorldRecord).build();
    }

    public void updateSport(Sport sport) {
        sport.setName(name);
        sport.setDescription(description);
        sport.setRules(rules);
        sport.setEquipment(equipment);
        sport.setPopularity(popularity);
        sport.setSportGender(sportGender);
        sport.setSportGameTimeInMinutes(sportGameTimeInMinutes);
        sport.setSportIntensity(sportIntensity);
        sport.setSportsTeam(sportsTeam);
        sport.setSportOlympic(sportOlympic);
        sport.setSportParalympic(sportParalympic);
        sport.setSportWorldRecord(sportWorldRecord);
    }

    public void setIntensity(IntensityEnum intensity) {
        this.sportIntensity = intensity;
    }

    public void setSportTeam(SportsTeam sportsTeam) {
        this.sportsTeam = sportsTeam;
    }
}
