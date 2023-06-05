package com.rails.railway.exceptions;

public class InvalidFareRequestException extends Exception{
    public InvalidFareRequestException(String message) {
        super(message);
    }
}
