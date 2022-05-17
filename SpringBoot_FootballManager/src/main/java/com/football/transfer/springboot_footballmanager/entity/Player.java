package com.football.transfer.springboot_footballmanager.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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

    @Min(17)
    @Column(name = "age")
    private int age;

    @Min(1)
    @Column(name = "experience")
    private int monthsOfExperience;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "team_id")
    @JsonIdentityReference(alwaysAsId = true)
    private FootballTeam team;

    public Player() {
    }

    public Player(String name, int age, int monthsOfExperience) {
        this.name = name;
        this.age = age;
        this.monthsOfExperience = monthsOfExperience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMonthsOfExperience() {
        return monthsOfExperience;
    }

    public void setMonthsOfExperience(int monthsOfExperience) {
        this.monthsOfExperience = monthsOfExperience;
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
                ", age=" + age +
                ", monthsOfExperience=" + monthsOfExperience +
                '}';
    }
}
