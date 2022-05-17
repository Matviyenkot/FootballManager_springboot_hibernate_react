package com.football.transfer.springboot_footballmanager.controller;

import com.football.transfer.springboot_footballmanager.RequestClasses.RequestTeam;
import com.football.transfer.springboot_footballmanager.entity.FootballTeam;
import com.football.transfer.springboot_footballmanager.handlers.ImplossibleToDeleteException;
import com.football.transfer.springboot_footballmanager.handlers.IncorrectDataPutException;
import com.football.transfer.springboot_footballmanager.handlers.NoSuchEntityException;
import com.football.transfer.springboot_footballmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private void updateTeamValidation(RequestTeam updateTeam, FootballTeam currentTeam, int id){
        //check if player with such id exist
        if(currentTeam == null){
            throw new NoSuchEntityException("There is no team with id: " + id);
        }
        else if(updateTeam.getName() == null || updateTeam.getCommission() < 0 && updateTeam.getCommission() > 10
                    || updateTeam.getFinances() < 0){
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

    @PutMapping("/update/{id}")
    public FootballTeam updateTeam(@RequestBody RequestTeam updateTeam, @PathVariable int id){

        FootballTeam currentTeam = getTeam(id);

        updateTeamValidation(updateTeam, currentTeam, id);

        return teamService.updateTeam(updateTeam, currentTeam);
    }

    @GetMapping("/get/{id}")
    public FootballTeam getTeam(@PathVariable int id){

        FootballTeam footballTeam = teamService.getTeam(id);

        if(footballTeam == null){
                throw new NoSuchEntityException("There is no team with id: " + id);
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
            throw new NoSuchEntityException("There is no team with id: " + id);
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
