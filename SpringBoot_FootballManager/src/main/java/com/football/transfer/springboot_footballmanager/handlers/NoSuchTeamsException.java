package com.football.transfer.springboot_footballmanager.handlers;

public class NoSuchTeamsException extends RuntimeException{

    public NoSuchTeamsException(String message) {
        super(message);
    }
}
