package com.handson.backend.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class SportsTeam implements Serializable {

    //region Members
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String sport;

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
