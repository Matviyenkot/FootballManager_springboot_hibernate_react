package com.football.transfer.springboot_footballmanager.dao.impl;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.dao.TeamDAO;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.entity.Player;
import com.football.transfer.springboot_footballmanager.handlers.NoSuchEntityException;
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

        session.save(footballTeam);
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

    @Override
    public FootballTeam updateTeam( FootballTeam currentTeam) {

        Session session = entityManager.unwrap(Session.class);

        session.update(currentTeam);

        return currentTeam;
    }
}
