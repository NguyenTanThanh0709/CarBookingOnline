package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Promotions")
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Code", length = 20)
    private String code;
    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
    private Boolean status;
    @Column(name = "DiscountAmount")
    private BigDecimal discountAmount;
    @OneToMany(mappedBy = "promotion")
    @JsonIgnore
    private List<Trip> trips;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}