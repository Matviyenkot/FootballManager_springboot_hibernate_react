package com.football.transfer.springboot_footballmanager.dao;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.entity.Player;

import java.util.List;

public interface TeamDAO {
    public List<FootballTeam> getAllTeams();

    public void saveTeam(FootballTeam footballTeam);

    public FootballTeam getTeam(int id);

    public void deleteTeam(int id);

    public FootballTeam updateTeam(RequestTeam team, FootballTeam currentTeam);

    public FootballTeam addPlayerToTeam(int teamId, int playerId);
}
