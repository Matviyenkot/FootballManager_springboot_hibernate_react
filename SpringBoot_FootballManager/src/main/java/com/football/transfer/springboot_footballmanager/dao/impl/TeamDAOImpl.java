package com.football.transfer.springboot_footballmanager.dao.impl;

import com.football.transfer.springboot_footballmanager.dao.TeamDAO;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.entity.Player;
import com.football.transfer.springboot_footballmanager.handlers.NoSuchTeamsException;
import com.football.transfer.springboot_footballmanager.handlers.NotEnoughMoneyException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TeamDAOImpl implements TeamDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<FootballTeam> getAllTeams() {

        Session session = entityManager.unwrap(Session.class);

        Query<FootballTeam> query = session.createQuery("from FootballTeam", FootballTeam.class);
        List<FootballTeam> allTeams = query.getResultList();

        return allTeams;
    }

    @Override
    public void saveTeam(FootballTeam footballTeam) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(footballTeam);
    }

    @Override
    public FootballTeam getTeam(int id) {

        Session session = entityManager.unwrap(Session.class);

        FootballTeam team = session.get(FootballTeam.class, id);
        return team;
    }

    @Override
    public void deleteTeam(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query<FootballTeam> query = session.createQuery("delete from FootballTeam where id =:teamId");
        query.setParameter("teamId", id);
        query.executeUpdate();
    }


    //player can have no team
    @Override
    public FootballTeam addPlayerToTeam(int teamId, int playerId) {

        double price = 0;

        Session session = entityManager.unwrap(Session.class);

        //player who is going to be transfered
        Player player = session.get(Player.class, playerId);
        if(player == null){
            throw new NoSuchTeamsException("There is no player with id: " + playerId);
        }

        //new team which is going to buy player
        FootballTeam newTeam = session.get(FootballTeam.class, teamId);
        if(newTeam == null){
            throw new NoSuchTeamsException("There is no team with id: " + teamId);
        }

        if(player.getTeam() != null){
            //player's current team
            FootballTeam playerCurrentTeam = player.getTeam();

            //finances of new team
            double newTeamFinances = newTeam.getFinances();

            //commission of new team
            double newTeamCommission = newTeam.getCommission()/100;

            price = player.getMonthsOfExperience() * 100000.0 / player.getAge();
            price = price + (price * newTeamCommission);

            price = Math.round(price);

            if(newTeamFinances < price){
                throw new NotEnoughMoneyException("New team don't have enough money to buy player!");
            }

            playerCurrentTeam.setFinances(playerCurrentTeam.getFinances() + price);

            newTeam.setFinances(newTeam.getFinances() - price);
        }

        newTeam.addPlayerToTeam(player);
        session.saveOrUpdate(newTeam);


        return newTeam;
    }


}
