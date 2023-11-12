package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status; // đã thanh toán - đã hủy

    @Column(name = "transDate")
    private String transDate;

    @Column(name = "amountt")
    private String amountt;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "trantype")
    private String trantype;

    @OneToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private Booking booking;

}
