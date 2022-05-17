package com.football.transfer.springboot_footballmanager.service;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;

import java.util.List;

public interface TeamService {
    public List<FootballTeam> getAllTeams();

    public void saveTeam(FootballTeam footballTeam);

    public FootballTeam updateTeam(RequestTeam footballTeam, FootballTeam currentTeam);

    public FootballTeam getTeam(int id);

    public void deleteTeam(int id);

    public FootballTeam addPlayerToTeam(int teamId, int playerId);

}
