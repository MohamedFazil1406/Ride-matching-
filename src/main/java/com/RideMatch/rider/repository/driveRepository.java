package com.RideMatch.rider.repository;

import com.RideMatch.rider.entity.AvailabilityStatus;
import com.RideMatch.rider.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface driveRepository extends JpaRepository<Driver,Long> {
    Optional<Driver> findById(Long driverId);

    Optional<Driver> findDriverByUsername(String username);

    Optional<Driver> findDriverByPhoneNumber(String phoneNumber);

    Boolean existsByUsername(String username);

    Boolean existsByPhoneNumber(String phoneNumber);

    List<Driver> findByStatus(AvailabilityStatus status);


}
