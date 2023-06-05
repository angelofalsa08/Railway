package com.rails.railway.controller;

import com.rails.railway.dto.response.Schedule;
import com.rails.railway.service.HomeRailwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeRailwayService homeRailwayService;

    @GetMapping("/train-schedules")
    public List<Schedule> getTrainSchedules(@RequestParam("origin") Optional<String> origin,
                                            @RequestParam("destination") Optional<String> destination,
                                            @RequestParam("date") Optional<String> date){
        Map<String,Object> requestMap = new HashMap<>();
        origin.ifPresent(o -> requestMap.put("origin",o));
       // requestMap.put("origin",origin);
        //requestMap.put("destination",destination);
        //requestMap.put("date",date);
        destination.ifPresent(o -> requestMap.put("destination",o));
        date.ifPresent(o -> requestMap.put("date",o));
        return homeRailwayService.getTrainSchedules(requestMap);
    }

    @GetMapping("/schedule-filter")
    public List<Schedule> filterTrainSchedules(){
        return null;
    }



}
