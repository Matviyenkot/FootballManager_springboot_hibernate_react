package com.football.transfer.springboot_footballmanager.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    public ResponseEntity<TeamsIncorrectData> handleException(NoSuchTeamsException noSuchTeamsException){

        TeamsIncorrectData data = new TeamsIncorrectData();
        data.setInfo(noSuchTeamsException.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TeamsIncorrectData> handleException(Exception exception){

        TeamsIncorrectData data = new TeamsIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<TeamsIncorrectData> handleException(ImplossibleToDeleteException exception){

        TeamsIncorrectData data = new TeamsIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<TeamsIncorrectData> handleException(NotEnoughMoneyException exception){

        TeamsIncorrectData data = new TeamsIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<TeamsIncorrectData> handleException(IncorrectDataPutException exception){

        TeamsIncorrectData data = new TeamsIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
