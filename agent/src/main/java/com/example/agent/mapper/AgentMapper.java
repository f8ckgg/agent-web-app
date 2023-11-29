package com.example.agent.mapper;

import com.example.agent.dto.AgentDTO;
import com.example.agent.entity.Agent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentMapper {
    AgentDTO toDTO(Agent agent);
    Agent toEntity(AgentDTO agentDTO);
}
