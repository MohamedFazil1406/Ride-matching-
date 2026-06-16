package com.RideMatch.rider.entity;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    private Double latitude;

    private Double longitude;
}
