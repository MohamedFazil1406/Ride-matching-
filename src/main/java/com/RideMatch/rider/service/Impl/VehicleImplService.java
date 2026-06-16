package com.RideMatch.rider.service.Impl;


import com.RideMatch.rider.entity.Driver;
import com.RideMatch.rider.entity.VehicleInfo;
import com.RideMatch.rider.repository.driveRepository;
import com.RideMatch.rider.repository.vehicleRepository;
import com.RideMatch.rider.service.vehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleImplService implements vehicleService {

    private final driveRepository driveRepository;

    private final vehicleRepository vehicleRepository;

    public VehicleImplService(driveRepository driveRepository, vehicleRepository vehicleRepository) {
        this.driveRepository = driveRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleInfo addVehicle(VehicleInfo vehicleInfo, Long driverId) {
        Driver existDrive = driveRepository.findById(driverId).orElse(null);

        if(existDrive==null){
            return null;
        }
        vehicleInfo.setDriver(existDrive);
        return vehicleRepository.save(vehicleInfo);

    }

    @Override
    public VehicleInfo getVehicle(Long vehicleId, Long driverId) {

        Driver existDriver = driveRepository.findById(driverId)
                .orElse(null);

        if (existDriver == null) {
            return null;
        }

        return vehicleRepository.findById(vehicleId)
                .orElse(null);
    }
}
