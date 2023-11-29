package com.example.agent.mapper;

import com.example.agent.dto.DealDTO;
import com.example.agent.entity.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealMapper {
    DealDTO toDTO(Deal deal);
    Deal toEntity(DealDTO dealDTO);
}