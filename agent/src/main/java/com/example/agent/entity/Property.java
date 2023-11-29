package com.example.agent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private double price;

    private String description;

    private boolean available;

    @ManyToOne
    private Agent agent;

}
