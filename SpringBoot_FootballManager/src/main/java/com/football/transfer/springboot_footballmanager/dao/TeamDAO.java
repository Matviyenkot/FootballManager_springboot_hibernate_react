package com.football.transfer.springboot_footballmanager.dao;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.entity.Player;

import java.util.List;

public interface TeamDAO {
    List<FootballTeam> getAllTeams();

    void saveTeam(FootballTeam footballTeam);

    FootballTeam getTeam(int id);

    void deleteTeam(int id);

    FootballTeam updateTeam(FootballTeam currentTeam);
}
