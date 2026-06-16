package com.RideMatch.rider.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rides")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Driver driverId;

    private Double pickupLat;
    private Double pickupLng;

    private Double destinationLat;
    private Double destinationLng;

    private Double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
}