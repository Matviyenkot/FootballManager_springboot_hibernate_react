package com.football.transfer.springboot_footballmanager.service.impl;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestPlayer;
import com.football.transfer.springboot_footballmanager.dao.PlayerDAO;
import com.football.transfer.springboot_footballmanager.entity.Player;
import com.football.transfer.springboot_footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    @Override
    @Transactional
    public Player getPlayer(int id) {

        return playerDAO.getPlayer(id);
    }

    @Override
    @Transactional
    public void savePlayer(Player player) {

        playerDAO.savePlayer(player);
    }

    @Override
    @Transactional
    public Player updatePlayerX(RequestPlayer player, Player currentPlayer) {


        return playerDAO.updatePlayerX(player, currentPlayer);
    }

}
