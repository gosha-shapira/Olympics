package com.handson.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SportsTeam implements Serializable {

    //region Members
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    @Getter
    @Setter
    private Sport sport;

    @Getter
    @Setter
    @NonNull
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Athlete> athletes;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String league;

    @Getter
    @Setter
    private String stadium;

    @Getter
    @Setter
    private String coach;

    @Getter
    @Setter
    private String foundation;

    @Getter
    @Setter
    private String website;

    @Getter
    @Setter
    private String logo;

    @Getter
    @Setter
    private String description;
    //endregion


    //update sports team
    public void updateSportsTeam(SportsTeam sportsTeam) {
        this.name = sportsTeam.getName();
        this.country = sportsTeam.getCountry();
        this.city = sportsTeam.getCity();
        this.league = sportsTeam.getLeague();
        this.stadium = sportsTeam.getStadium();
        this.coach = sportsTeam.getCoach();
        this.foundation = sportsTeam.getFoundation();
        this.website = sportsTeam.getWebsite();
        this.logo = sportsTeam.getLogo();
        this.description = sportsTeam.getDescription();
    }
    //endregion


}
