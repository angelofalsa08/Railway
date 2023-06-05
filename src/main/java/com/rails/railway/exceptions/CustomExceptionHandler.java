package com.rails.railway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidFareRequestException.class)
    @ResponseBody
    public Map<String, String> handleInvalidFareRequest(InvalidFareRequestException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("errorMessage", exception.getMessage());
        return map;
    }
}
