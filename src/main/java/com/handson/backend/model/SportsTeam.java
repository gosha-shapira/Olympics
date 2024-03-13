package com.handson.backend.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "team")
    @Getter
    @Setter
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

    //region Constructors
    public SportsTeam() {
    }

    //endregion


}
