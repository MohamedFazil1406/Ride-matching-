package com.RideMatch.rider.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is Required")
    @Column(unique=true)
    private String username;

    @NotBlank(message="Password Is Required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private Location location;

    @NotBlank(message = "Phone Number is required")
    @Column(unique=true)
    private String phoneNumber;
}


