package com.football.transfer.springboot_footballmanager.handlers;

public class NoSuchEntityException extends RuntimeException{

    public NoSuchEntityException(String message) {
        super(message);
    }
}
