package com.football.transfer.springboot_footballmanager.service;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;

import java.util.List;

public interface TeamService {
    List<FootballTeam> getAllTeams();

    void saveTeam(FootballTeam footballTeam);

    FootballTeam updateTeam(RequestTeam footballTeam, FootballTeam currentTeam);

    FootballTeam getTeam(int id);

    void deleteTeam(int id);

    FootballTeam addPlayerToTeam(int teamId, int playerId);

}
