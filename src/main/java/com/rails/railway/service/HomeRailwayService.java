package com.rails.railway.service;

import com.rails.railway.dto.response.Schedule;

import java.util.List;
import java.util.Map;

public interface HomeRailwayService {
    List<Schedule> getTrainSchedules(Map<String, Object> map);
}
