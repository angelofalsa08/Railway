package com.rails.railway.controller;

import com.rails.railway.dto.requests.FareRequest;
import com.rails.railway.dto.response.FareResponse;
import com.rails.railway.exceptions.InvalidFareRequestException;
import com.rails.railway.model.Fare;
import com.rails.railway.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fare")
public class FareController {

    @Autowired
    private FareService fairService;

    @PostMapping("/compute")
    public ResponseEntity<FareResponse> computeFare(@RequestBody FareRequest fareRequest) throws InvalidFareRequestException{
        FareResponse fare = null;
        try{
            fare = fairService.computeFareAndDiscounts(fareRequest);
        }catch (InvalidFareRequestException e){
            throw new InvalidFareRequestException(e.getMessage());
        }
        return new ResponseEntity<>(fare, HttpStatus.OK);
    }
}
