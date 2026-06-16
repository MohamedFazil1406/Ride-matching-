package com.RideMatch.rider.service.Impl;

import com.RideMatch.rider.entity.AvailabilityStatus;
import com.RideMatch.rider.entity.Driver;
import com.RideMatch.rider.repository.driveRepository;
import com.RideMatch.rider.service.driveService;
import com.RideMatch.rider.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class driverImplService implements driveService {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private final driveRepository driveRepository;

    public driverImplService(driveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    @Override
    public Driver register(Driver driver) {
        Driver existDriver = driveRepository.findDriverByUsername(driver.getUsername()).orElse(null);

        if (existDriver != null) {
            return null;
        }

        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        return driveRepository.save(driver);

    }

    @Override
    public String login(Driver driver) {
        Driver existDriver = driveRepository.findDriverByUsername(driver.getUsername()).orElse(null);

        if (existDriver == null) {
            return null;
        }

        boolean passwordMatch = passwordEncoder.matches(driver.getPassword(), existDriver.getPassword());

        if (!passwordMatch) {
            return null;
        }

        return jwtUtil.generateToken(existDriver.getUsername(),existDriver.getId());
    }

    @Override
    public AvailabilityStatus getAvailabilityStatus(Long driverId) {
        Driver driver = driveRepository.findById(driverId).orElse(null);

        if (driver == null) {
            return null;
        }


        return driver.getStatus();
    }

    @Override
    public AvailabilityStatus updateAvailabilityStatus(Long DriverId, AvailabilityStatus availabilityStatus) {
        Driver driver =  driveRepository.findById(DriverId).orElse(null);

        if (driver == null) {
            return null;
        }

        driver.setStatus(availabilityStatus);
        driveRepository.save(driver);
        return driver.getStatus();
    }
}
