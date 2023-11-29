package com.example.agent.controller;

import com.example.agent.dto.DealDTO;
import com.example.agent.dto.PropertyDTO;
import com.example.agent.dto.UserDTO;
import com.example.agent.entity.User;
import com.example.agent.service.DealService;
import com.example.agent.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/deals")
public class DealController {
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    public ResponseEntity<List<DealDTO>> getAllDeals() {
        List<DealDTO> deals = dealService.getAllDeals();
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealDTO> getDealById(@PathVariable Long id) {
        DealDTO deal = dealService.getDealById(id);
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DealDTO> createDeal(@RequestBody DealDTO dealDTO) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        dealDTO.setBuyer(userDTO);
        DealDTO createdDeal = dealService.createDeal(dealDTO);
        return new ResponseEntity<>(createdDeal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealDTO> updateDeal(@PathVariable Long id, @RequestBody DealDTO dealDTO) {
        dealDTO.setId(id);
        DealDTO updatedDeal = dealService.updateDeal(dealDTO);
        return new ResponseEntity<>(updatedDeal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byBuyerId")
    public ResponseEntity<List<DealDTO>> getAllDealsByBuyerId(){
    Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<DealDTO> deals = dealService.getAllDealsByBuyerId(id);
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }
    @PutMapping("/approve/{id}")
    public ResponseEntity<DealDTO> approveDeal(@PathVariable Long id) {
        DealDTO updatedDeal = dealService.approveDeal(id);
        return ResponseEntity.ok(updatedDeal);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<DealDTO> rejectDeal(@PathVariable Long id) {
        DealDTO updatedDeal = dealService.rejectDeal(id);
        return ResponseEntity.ok(updatedDeal);
    }
}
