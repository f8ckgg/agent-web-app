package com.example.agent.mapper;

import com.example.agent.dto.RatingDTO;
import com.example.agent.entity.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AgentMapper.class)
public interface RatingMapper {
    RatingDTO toDTO(Rating rating);
    Rating toEntity(RatingDTO ratingDTO);
}
