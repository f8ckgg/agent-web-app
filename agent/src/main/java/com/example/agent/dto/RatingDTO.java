package com.example.agent.dto;

import com.example.agent.entity.User;
import lombok.*;

@Getter
@Setter
public class RatingDTO {
    private Long id;
    private UserDTO user;
    private AgentDTO agent;
    private double value;
    private String comment;
}
