package com.carbooking.carbookingonlineserver.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String email;
    private String title;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String company;

}
