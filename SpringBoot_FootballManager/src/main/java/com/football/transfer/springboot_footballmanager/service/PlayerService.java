package com.football.transfer.springboot_footballmanager.service;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestPlayer;
import com.football.transfer.springboot_footballmanager.entity.Player;

public interface PlayerService {

    public Player getPlayer(int id);

    public void savePlayer(Player player);

    public Player updatePlayerX(RequestPlayer player, Player currentPlayer);


}
