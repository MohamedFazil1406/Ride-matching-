package com.RideMatch.rider.controller;


import com.RideMatch.rider.entity.Principle;
import com.RideMatch.rider.entity.VehicleInfo;
import com.RideMatch.rider.service.vehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicleInfo")
public class vehicleController {

    private final vehicleService vehicleService;

    public vehicleController(vehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add-vehicle")
    public ResponseEntity<?> addVehicle(
            @RequestBody VehicleInfo vehicleInfo) {

        Principle principle = (Principle)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        VehicleInfo vehicle =
                vehicleService.addVehicle(
                        vehicleInfo,
                        principle.getId()
                );

        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable Long vehicleId) {

        Principle principle = (Principle)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        Long driverId = principle.getId();

        VehicleInfo vehicle =
                vehicleService.getVehicle(vehicleId, driverId);

        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vehicle not found");
        }

        return ResponseEntity.ok(vehicle);
    }

}
