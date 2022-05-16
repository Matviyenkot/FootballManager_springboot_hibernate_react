package com.football.transfer.springboot_footballmanager.controller;

import com.football.transfer.springboot_footballmanager.dao.PlayerRepo;
import com.football.transfer.springboot_footballmanager.dao.TeamDAO;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.handlers.ImplossibleToDeleteException;
import com.football.transfer.springboot_footballmanager.handlers.IncorrectDataPutException;
import com.football.transfer.springboot_footballmanager.handlers.NoSuchTeamsException;
import com.football.transfer.springboot_footballmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*" , methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/teams")
public class TeamsController {


    @Autowired
    private TeamService teamService;

    private void validateTeam(FootballTeam team){
        if(team.getCommission() < 0 && team.getCommission() >10 ||
                team.getName().isEmpty() || team.getFinances() < 0){
            throw new IncorrectDataPutException("You had input wrong data! Check it please");
        }
    }

    @GetMapping("/get")
    public List<FootballTeam> getAllTeams(){

        List<FootballTeam> footballTeams = teamService.getAllTeams();

        return footballTeams;
    }

    @PostMapping("/create")
    public FootballTeam addNewTeam(@RequestBody FootballTeam footballTeam){

        validateTeam(footballTeam);


        teamService.saveTeam(footballTeam);
        return footballTeam;
    }

    @PutMapping("/update")
    public FootballTeam updateTeam(@RequestBody FootballTeam footballTeam){

        validateTeam(footballTeam);

        teamService.saveTeam(footballTeam);
        return footballTeam;
    }

    @GetMapping("/get/{id}")
    public FootballTeam getTeam(@PathVariable int id){

        FootballTeam footballTeam = teamService.getTeam(id);

        if(footballTeam == null){
                throw new NoSuchTeamsException("There is no team with id: " + id);
        }

        return footballTeam;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable int id){

        FootballTeam team = teamService.getTeam(id);

        if(!team.getPlayers().isEmpty()){
            throw new ImplossibleToDeleteException("You can not delete team with players!");
        }
        else if(team == null){
            throw new NoSuchTeamsException("There is no team with id: " + id);
        }
        teamService.deleteTeam(id);
    }

    //add to team {teamId} player {playerId}
    @PutMapping("/{teamId}/players/{playerId}")
    public FootballTeam addPlayerToTeam(@PathVariable int teamId, @PathVariable int playerId){

        FootballTeam footballTeam1 = teamService.addPlayerToTeam(teamId, playerId);


        return footballTeam1;
    }



}
