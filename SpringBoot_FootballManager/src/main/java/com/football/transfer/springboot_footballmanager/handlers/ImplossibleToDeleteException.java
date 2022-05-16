package com.football.transfer.springboot_footballmanager.handlers;

public class ImplossibleToDeleteException extends RuntimeException{
    public ImplossibleToDeleteException(String message) {
        super(message);
    }
}
