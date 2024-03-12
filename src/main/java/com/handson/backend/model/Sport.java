package com.handson.backend.model;

import com.handson.backend.enums.IntensityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sport")
public class Sport implements Serializable {
    //region Members
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @NotNull
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();
    //endregion
}
