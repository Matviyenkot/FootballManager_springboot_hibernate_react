package com.football.transfer.springboot_footballmanager.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Validated
@Entity
@Table(name = "players")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
@DynamicUpdate
@SelectBeforeUpdate(value = false)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "team_id", updatable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private FootballTeam team;

    public Player() {
    }

    public Player(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @JsonIgnore
    public double getPlayerPrice(){

        return getPlayerMonthsOfExperience() * 100000.0 / getPlayerAge();
    }

    @JsonIgnore
    public int getPlayerAge(){

        int age = LocalDate.now().getYear() - birthDate.getYear();

        return age;
    }

    @JsonIgnore
    public int getPlayerMonthsOfExperience(){

        LocalDate startPlaying = birthDate.plusYears(17);

        int monthsOfExperience = (LocalDate.now().getYear() - startPlaying.getYear()) * 12
                + ( Math.abs (LocalDate.now().getMonthValue() -startPlaying.getMonthValue() ) );

        return monthsOfExperience;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FootballTeam getTeam() {
        return team;
    }

    public void setTeam(FootballTeam team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
