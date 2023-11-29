package com.example.agent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private String email;

    private String licenseNumber;
}
