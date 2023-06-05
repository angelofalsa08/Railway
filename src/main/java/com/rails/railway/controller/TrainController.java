package com.rails.railway.controller;

import com.rails.railway.model.Train;
import com.rails.railway.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/train")
    public ResponseEntity<String> addTrain(@RequestBody Train train){
        trainService.addTrain(train);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }

    @PutMapping("/train/{trainId}")
    public ResponseEntity<String> updateTrain(@PathVariable("trainId") Long trainId){
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/train/{trainId}")
    public ResponseEntity<String> deleteTrain(@PathVariable("trainId") Long trainId){

        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}
