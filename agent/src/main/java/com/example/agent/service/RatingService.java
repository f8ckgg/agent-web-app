package com.example.agent.service;

import com.example.agent.dto.RatingDTO;
import com.example.agent.entity.Rating;
import com.example.agent.entity.User;
import com.example.agent.mapper.RatingMapper;
import com.example.agent.repository.RatingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }


    public RatingDTO createRating(RatingDTO ratingDTO) {
        Rating rating = ratingMapper.toEntity(ratingDTO);
        Rating createdRating = ratingRepository.save(rating);
        return ratingMapper.toDTO(createdRating);
    }


    public double getAverageRating(long id) {
        List<Rating> ratings = ratingRepository.findAllByAgentId(id);
        double sum = 0.0;
        for (Rating rating : ratings) {
            sum += rating.getValue();
        }
        return sum / ratings.size();
    }
}