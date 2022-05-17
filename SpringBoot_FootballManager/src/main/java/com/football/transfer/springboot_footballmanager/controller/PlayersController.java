package com.football.transfer.springboot_footballmanager.controller;


import com.football.transfer.springboot_footballmanager.dao.PlayerRepo;
import com.football.transfer.springboot_footballmanager.entity.Player;
import com.football.transfer.springboot_footballmanager.handlers.IncorrectDataPutException;
import com.football.transfer.springboot_footballmanager.handlers.NoSuchTeamsException;
import com.football.transfer.springboot_footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/players")
public class PlayersController {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private PlayerService playerService;


    private void validatePlayer(Player player){
        if(player.getAge() < 18 || player.getName().isEmpty() || player.getMonthsOfExperience() < 1){
            throw new IncorrectDataPutException("You had input wrong data! Check it please");
        }
    }

    @GetMapping("/get")
    private List<Player> getAllPlayers(){

        List<Player> allPlayers = playerRepo.findAll();


        return allPlayers;
    }

    @GetMapping("/get/{id}")
    public Player getPlayer(@PathVariable int id){

        Player player = playerService.getPlayer(id);

        if(player == null){
            throw new NoSuchTeamsException("There is no player with id: " + id);
        }

        return player;
    }



    @PostMapping("/create")
    public Player addNewPlayer(@RequestBody Player player){

        validatePlayer(player);

        playerService.savePlayer(player);
        return player;
    }


    @PutMapping("/update")
    public Player updatePlayer(@RequestBody Player player){

        if(player.getAge() < 18 && player.getAge()!=0 || player.getMonthsOfExperience() < 0){
            throw new IncorrectDataPutException("You had input wrong data! Check it please");
        }
        return playerService.updatePlayer(player);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayer(@PathVariable int id){

        Player player = playerService.getPlayer(id);

        if(player == null){
            throw new NoSuchTeamsException("There is no player with id: " + id);
        }
        playerRepo.deleteById(id);
    }



}
