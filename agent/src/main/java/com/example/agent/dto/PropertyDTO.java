package com.example.agent.dto;

import lombok.*;

@Getter
@Setter
public class PropertyDTO {
    private Long id;
    private String address;
    private double price;
    private String description;
    private boolean available;
    private AgentDTO agent;

}
