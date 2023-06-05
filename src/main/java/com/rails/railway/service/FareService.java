package com.rails.railway.service;

import com.rails.railway.dto.requests.FareRequest;
import com.rails.railway.dto.response.FareResponse;
import com.rails.railway.exceptions.InvalidFareRequestException;
import com.rails.railway.model.Fare;

import java.util.List;

public interface FareService {
    FareResponse computeFareAndDiscounts(FareRequest fareRequest) throws InvalidFareRequestException;
}
