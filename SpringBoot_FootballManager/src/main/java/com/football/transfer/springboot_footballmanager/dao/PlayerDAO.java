package com.football.transfer.springboot_footballmanager.dao;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestPlayer;
import com.football.transfer.springboot_footballmanager.entity.Player;

public interface PlayerDAO {

    Player getPlayer(int id);

    void savePlayer(Player player);

    Player updatePlayerX(Player currentPlayer);


}
