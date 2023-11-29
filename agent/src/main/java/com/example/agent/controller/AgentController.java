package com.example.agent.controller;

import com.example.agent.dto.AgentDTO;
import com.example.agent.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {
    private final AgentService agentService;
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        List<AgentDTO> agents = agentService.getAllAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id) {
        AgentDTO agent = agentService.getAgentById(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AgentDTO> createAgent(@RequestBody AgentDTO agentDTO) {
        AgentDTO createdAgent = agentService.createAgent(agentDTO);
        return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentDTO> updateAgent(@PathVariable Long id, @RequestBody AgentDTO agentDTO) {
        agentDTO.setId(id);
        AgentDTO updatedAgent = agentService.updateAgent(agentDTO);
        return new ResponseEntity<>(updatedAgent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
