package com.football.transfer.springboot_footballmanager.handlers;

public class IncorrectDataPutException extends RuntimeException{
    public IncorrectDataPutException(String message) {
        super(message);
    }
}
