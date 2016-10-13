package com.ccs.contacts.api.dto;

public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String message) {
        super(message);
    }
}