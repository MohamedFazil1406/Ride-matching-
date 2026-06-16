package com.RideMatch.rider.service;


import com.RideMatch.rider.entity.Driver;
import com.RideMatch.rider.entity.VehicleInfo;

public interface vehicleService {

    VehicleInfo addVehicle(VehicleInfo vehicleInfo, Long driverId);



    VehicleInfo getVehicle(Long vehicleId, Long driverId);

}
