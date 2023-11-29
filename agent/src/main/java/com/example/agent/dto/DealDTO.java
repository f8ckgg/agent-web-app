package com.example.agent.dto;

import lombok.*;

@Getter
@Setter
public class DealDTO {
    private Long id;
    private UserDTO buyer;
    private PropertyDTO property;
    private String status;
}
