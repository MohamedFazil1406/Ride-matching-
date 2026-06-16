package com.RideMatch.rider.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name= "Driver")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is Required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password Is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private Location location;

    @NotBlank(message = "Phone Number Is required")
    @Column(unique = true)
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private AvailabilityStatus status;


}

