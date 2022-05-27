package com.football.transfer.springboot_footballmanager.service.impl;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.dao.PlayerDAO;
import com.football.transfer.springboot_footballmanager.dao.TeamDAO;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.entity.Player;
import com.football.transfer.springboot_footballmanager.handlers.IncorrectDataPutException;
import com.football.transfer.springboot_footballmanager.handlers.NoSuchEntityException;
import com.football.transfer.springboot_footballmanager.handlers.NotEnoughMoneyException;
import com.football.transfer.springboot_footballmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private PlayerDAO playerDAO;

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
    @Transactional
    public FootballTeam updateTeam(RequestTeam updateTeam, FootballTeam currentTeam) {

        currentTeam.setName(updateTeam.getName());
        currentTeam.setFinances(updateTeam.getDoubleFinances());
        currentTeam.setCommission(updateTeam.getCommission());

        return teamDAO.updateTeam(currentTeam);
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

        double price = 0;

        Player player = playerDAO.getPlayer(playerId);
        if(player == null){
            throw new NoSuchEntityException("There is no player with id: " + playerId);
        }

        //new team which is going to buy player
        FootballTeam newTeam = teamDAO.getTeam(teamId);
        if(newTeam == null){
            throw new NoSuchEntityException("There is no team with id: " + teamId);
        }

        if(player.getTeam() != null){
            //player's current team
            FootballTeam playerCurrentTeam = player.getTeam();

            if(playerCurrentTeam.getId() == newTeam.getId()){
                throw new IncorrectDataPutException("Player is already in that team! " +
                        "You can not transfer player to the same team!");
            }

            //finances of new team
            double newTeamFinances = newTeam.getDoubleFinances();

            //commission of new team
            double newTeamCommission = newTeam.getCommission()/100;

            price = player.getPlayerPrice();
            price = price + (price * newTeamCommission);

            price = Math.round(price);

            if(newTeamFinances < price){
                throw new NotEnoughMoneyException("New team don't have enough money to buy player!");
            }

            playerCurrentTeam.setFinances(playerCurrentTeam.getDoubleFinances() + price);

            newTeam.setFinances(newTeam.getDoubleFinances() - price);
        }

        newTeam.addPlayerToTeam(player);

        return teamDAO.updateTeam(newTeam);
    }
}
