package com.RideMatch.rider.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String vehicleNumber;

    @NotBlank
    private String model;

    @NotBlank
    private String brand;

    @NotBlank
    private String color;

    @NotNull
    private Integer seatingCapacity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Driver driver;
}
