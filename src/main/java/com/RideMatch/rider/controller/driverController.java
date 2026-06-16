package com.RideMatch.rider.controller;


import com.RideMatch.rider.entity.AvailabilityStatus;
import com.RideMatch.rider.entity.Driver;
import com.RideMatch.rider.entity.Principle;
import com.RideMatch.rider.service.driveService;
import com.RideMatch.rider.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class driverController {



    private final driveService driveService;

    public driverController(driveService driveService) {
        this.driveService = driveService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDriver(@RequestBody Driver driver){
        Driver existDriver = driveService.register(driver);

        if(existDriver == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Driver registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginDriver(@RequestBody Driver driver){
        String token = driveService.login(driver);

        if(token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(token);

    }

    @PutMapping("/availability-status")
    public ResponseEntity<?> availabilityStatus(
            @RequestBody AvailabilityStatus availabilityStatus) {

        Principle principle = (Principle)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        Long driverId = principle.getId();

        AvailabilityStatus updated =
                driveService.updateAvailabilityStatus(
                        driverId,
                        availabilityStatus);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Driver not found");
        }

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/availability-status")
    public ResponseEntity<?> getAvailabilityStatus() {

        Principle principle = (Principle)
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        Long driverId = principle.getId();

        AvailabilityStatus status =
                driveService.getAvailabilityStatus(driverId);

        return ResponseEntity.ok(status);
    }
}
