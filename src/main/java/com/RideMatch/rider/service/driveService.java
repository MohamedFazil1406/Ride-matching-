package com.RideMatch.rider.service;

import com.RideMatch.rider.entity.AvailabilityStatus;
import com.RideMatch.rider.entity.Driver;
import com.RideMatch.rider.entity.User;

public interface driveService {

    Driver register(Driver driver);

    String login(Driver driver);

    AvailabilityStatus getAvailabilityStatus(Long DriverId);

    AvailabilityStatus updateAvailabilityStatus(Long DriverId, AvailabilityStatus availabilityStatus);
}
