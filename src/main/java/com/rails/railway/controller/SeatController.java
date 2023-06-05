package com.rails.railway.controller;

import com.rails.railway.model.PremiumServices;
import com.rails.railway.model.Seat;
import com.rails.railway.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/select")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/seats/{trainId}")
    public List<Seat> getSeatSelectionList(@PathVariable("trainId") Long trainId){
        return seatService.getSeatSelections(trainId);
    }

    @GetMapping("/seats/services")
    public List<Map<String,Double>> getPremiumServices(){
        PremiumServices service = new PremiumServices();
        return Collections.singletonList(service.getAdditionalServices());
    }

}
