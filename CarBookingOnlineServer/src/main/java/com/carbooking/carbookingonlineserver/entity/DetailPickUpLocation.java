package com.carbooking.carbookingonlineserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "DetailPickUpLocation")
public class DetailPickUpLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "detailLocation", length = 255)
    private String detailLocation;

    @Column(name = "timepickup")
    private LocalTime time;

    private Long idtrip;

    @ManyToOne
    @JoinColumn(name = "id_trip")
    @JsonIgnore
    private Trip trip;
}
