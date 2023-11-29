package com.example.agent.mapper;

import com.example.agent.dto.PropertyDTO;
import com.example.agent.entity.Property;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AgentMapper.class)
public interface PropertyMapper {
    PropertyDTO toDTO(Property property);
    Property toEntity(PropertyDTO propertyDTO);
}
