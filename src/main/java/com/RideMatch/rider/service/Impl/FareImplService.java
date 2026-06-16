package com.RideMatch.rider.service.Impl;

import org.springframework.stereotype.Service;

@Service
public class FareImplService {
    private static final double BASE_FARE = 50;

    private static final double PER_KM = 12;

    public double calculateFare(
            double distanceKm) {

        return BASE_FARE +
                (distanceKm * PER_KM);
    }
}
