package com.football.transfer.springboot_footballmanager.dao.impl;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestPlayer;
import com.football.transfer.springboot_footballmanager.dao.PlayerDAO;
import com.football.transfer.springboot_footballmanager.entity.Player;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PlayerDAOImpl implements PlayerDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Player getPlayer(int id) {
        Session session = entityManager.unwrap(Session.class);

        Player player = session.get(Player.class, id);

        return player;
    }

    @Override
    public void savePlayer(Player player) {

        Session session = entityManager.unwrap(Session.class);

        session.save(player);
    }


    @Override
<<<<<<< HEAD
    public Player updatePlayer(Player player){

        Session session = entityManager.unwrap(Session.class);

        session.merge(player);
=======
    public Player updatePlayerX(RequestPlayer player, Player currentPlayer) {

        Session session = entityManager.unwrap(Session.class);

        currentPlayer.setName(player.getName());
        currentPlayer.setBirthDate(player.getBirthDate());
>>>>>>> fixedUpdate

        session.update(currentPlayer);

        return currentPlayer;
    }

}

