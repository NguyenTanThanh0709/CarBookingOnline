package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "TypeCar")
public class TypeCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "numberofseats")
    private Integer numberOfSeats;

    private Boolean status;

    @OneToMany(mappedBy = "typeCar")
    @JsonIgnore
    private List<Car> cars;

}
