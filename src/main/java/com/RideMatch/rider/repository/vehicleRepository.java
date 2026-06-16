package com.RideMatch.rider.repository;

import com.RideMatch.rider.entity.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface vehicleRepository extends JpaRepository<VehicleInfo,Long> {


    Optional<VehicleInfo> findById(Long driverId);

    boolean existsById(Long aLong);
}
