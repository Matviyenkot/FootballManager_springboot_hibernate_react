package com.football.transfer.springboot_footballmanager.dao;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestPlayer;
import com.football.transfer.springboot_footballmanager.entity.Player;

public interface PlayerDAO {

    public Player getPlayer(int id);

    public void savePlayer(Player player);

    public Player updatePlayerX(RequestPlayer player, Player currentPlayer);


}
