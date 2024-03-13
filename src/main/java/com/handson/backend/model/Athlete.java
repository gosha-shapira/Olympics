package com.handson.backend.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "athlete")
public class Athlete implements Serializable {
    //region Members
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();

    @NotEmpty
    @Length(max = 60)
    private String fullName;

    @Max(value = 65, message = "The age must be less than ${value}.")
    @Min(value = 16, message = "The age must be grater than ${value}.")
    private Integer age;

    @NotEmpty
    @Length(max = 60)
    private String mainSport;

    @Length(max = 60)
    private String optionalSport;

    @Length(max = 60)
    private String nationality;

    @Length(max = 500)
    private String profilePicture;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @Getter
    @Setter
    private SportsTeam team;
    //endregion
}