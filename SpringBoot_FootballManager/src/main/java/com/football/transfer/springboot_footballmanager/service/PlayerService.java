package com.football.transfer.springboot_footballmanager.service;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestPlayer;
import com.football.transfer.springboot_footballmanager.entity.Player;

public interface PlayerService {

    Player getPlayer(int id);

    void savePlayer(Player player);

    Player updatePlayerX(RequestPlayer player, Player currentPlayer);


}
