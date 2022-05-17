package com.football.transfer.springboot_footballmanager.service.impl;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.dao.TeamDAO;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDAO teamDAO;

    @Override
    @Transactional
    public List<FootballTeam> getAllTeams() {

        return teamDAO.getAllTeams();
    }

    @Override
    @Transactional
    public void saveTeam(FootballTeam footballTeam) {
        teamDAO.saveTeam(footballTeam);
    }

    @Override
    public FootballTeam updateTeam(RequestTeam updateTeam, FootballTeam currentTeam) {


        return teamDAO.updateTeam(updateTeam, currentTeam);
    }

    @Override
    @Transactional
    public FootballTeam getTeam(int id) {
        return teamDAO.getTeam(id);
    }

    @Override
    @Transactional
    public void deleteTeam(int id) {

        teamDAO.deleteTeam(id);
    }

    @Override
    @Transactional
    public FootballTeam addPlayerToTeam(int teamId, int playerId) {
        return teamDAO.addPlayerToTeam(teamId, playerId);
    }
}
