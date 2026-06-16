package com.RideMatch.rider.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RideRequestDto {
    private Long riderId;

    private Double pickupLat;

    private Double pickupLng;

    private Double destinationLat;

    private Double destinationLng;

    private String vehicleType;
}
