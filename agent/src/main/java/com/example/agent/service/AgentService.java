package com.example.agent.service;

import com.example.agent.dto.AgentDTO;
import com.example.agent.entity.Agent;
import com.example.agent.mapper.AgentMapper;
import com.example.agent.repository.AgentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentService {
    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    public AgentService(AgentRepository agentRepository, AgentMapper agentMapper) {
        this.agentRepository = agentRepository;
        this.agentMapper = agentMapper;
    }

    public List<AgentDTO> getAllAgents() {
        return agentRepository.findAll().stream()
                .map(agentMapper::toDTO)
                .sorted(Comparator.comparing(AgentDTO::getId).reversed())
                .collect(Collectors.toList());
    }

    public AgentDTO getAgentById(Long id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent not found"));
        return agentMapper.toDTO(agent);
    }

    public AgentDTO createAgent(AgentDTO agentDTO) {
        Agent agent = agentMapper.toEntity(agentDTO);
        Agent createdAgent = agentRepository.save(agent);
        return agentMapper.toDTO(createdAgent);
    }

    public AgentDTO updateAgent(AgentDTO agentDTO) {
        Agent agent = agentMapper.toEntity(agentDTO);
        Agent updatedAgent = agentRepository.save(agent);
        return agentMapper.toDTO(updatedAgent);
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }
}

