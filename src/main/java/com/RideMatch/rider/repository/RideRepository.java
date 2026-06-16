package com.RideMatch.rider.repository;

import com.RideMatch.rider.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
