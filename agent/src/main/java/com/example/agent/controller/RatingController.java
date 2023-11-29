package com.example.agent.controller;

import com.example.agent.dto.AgentDTO;
import com.example.agent.dto.RatingDTO;
import com.example.agent.dto.UserDTO;
import com.example.agent.entity.User;
import com.example.agent.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PutMapping("/{agentId}")
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO,@PathVariable Long agentId) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        ratingDTO.setUser(userDTO);
        AgentDTO agentDTO = new AgentDTO();
        agentDTO.setId(agentId);
        ratingDTO.setAgent(agentDTO);
        RatingDTO createdRating = ratingService.createRating(ratingDTO);
        return new ResponseEntity<>(createdRating, HttpStatus.CREATED);
    }


    @GetMapping("/average/{agentId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long agentId) {
        double averageRating = ratingService.getAverageRating(agentId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
}
