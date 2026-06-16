package com.RideMatch.rider.service.Impl;

import com.RideMatch.rider.entity.AvailabilityStatus;
import com.RideMatch.rider.entity.Driver;
import com.RideMatch.rider.repository.driveRepository;
import com.RideMatch.rider.util.DistanceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchinService {

    private final driveRepository driverRepository;

    public Driver findNearestDriver(
            Double pickupLat,
            Double pickupLng) {

        List<Driver> availableDrivers =
                driverRepository.findByStatus(
                        AvailabilityStatus.AVAILABLE);

        return availableDrivers.stream()
                .min(
                        Comparator.comparingDouble(
                                driver ->
                                        DistanceUtil.calculateDistance(
                                                pickupLat,
                                                pickupLng,
                                                driver.getLocation().getLatitude(),
                                                driver.getLocation().getLongitude())))
                .orElseThrow(
                        () -> new RuntimeException(
                                "No Drivers Available"));
    }
}