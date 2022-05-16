package com.football.transfer.springboot_footballmanager.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Validated
@Entity
@Table(name = "teams")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Integer.class)
public class FootballTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @NotBlank
    @Column(name = "name")
    private String name;

    @Min(0)
    @Max(10)
    @Column(name = "commission")
    private double commission;

    @Min(0)
    @Column(name = "finances")
    private double  finances;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "team")
    @JsonIdentityReference(alwaysAsId = false)
    private List<Player> players;

    public FootballTeam() {
    }

    public FootballTeam(String name, double commission, double finances) {
        this.name = name;
        this.commission = commission;
        this.finances = finances;
    }

    public void addPlayerToTeam(Player player){
        if(players == null){
            players = new ArrayList<>();
        }

        players.add(player);
        player.setTeam(this);
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

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getFinances() {
        return finances;
    }

    public void setFinances(double finances) {
        this.finances = finances;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "FootballTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commission=" + commission +
                ", finances=" + finances +
                '}';
    }
}
