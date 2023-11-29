package com.example.agent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private Property property;


    private String status ;



}
