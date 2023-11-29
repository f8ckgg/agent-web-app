package com.example.agent.service;

import com.example.agent.dto.DealDTO;
import com.example.agent.entity.Deal;
import com.example.agent.mapper.DealMapper;
import com.example.agent.repository.DealRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;
    private final PropertyService propertyService;

    public DealService(DealRepository dealRepository, DealMapper dealMapper,
                      PropertyService  propertyService) {
        this.dealRepository = dealRepository;
        this.dealMapper = dealMapper;
        this.propertyService=propertyService;
    }

    public List<DealDTO> getAllDeals() {
        return dealRepository.findAll().stream()
                .map(dealMapper::toDTO)
                .sorted(Comparator.comparing(DealDTO::getId).reversed())
                .collect(Collectors.toList());
    }

    public DealDTO getDealById(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deal not found"));
        return dealMapper.toDTO(deal);
    }

    public DealDTO createDeal(DealDTO dealDTO) {
        Deal deal = dealMapper.toEntity(dealDTO);
        Deal deal2 = updatePropertyAvailability(deal);
        Deal createdDeal = dealRepository.save(deal2);
        return dealMapper.toDTO(createdDeal);
    }

    public DealDTO updateDeal(DealDTO dealDTO) {
        Deal deal = dealMapper.toEntity(dealDTO);
        Deal updatedDeal = dealRepository.save(deal);
        return dealMapper.toDTO(updatedDeal);
    }

    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }

    public List<DealDTO> getAllDealsByBuyerId(Long userId) {
        return dealRepository.findAllByBuyerId(userId).stream()
                .map(dealMapper::toDTO)
                .sorted(Comparator.comparing(DealDTO::getId).reversed())
                .collect(Collectors.toList());
    }
    public DealDTO approveDeal(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deal not found"));
        deal.setStatus("APPROVED");
        Deal deal2 = updatePropertyAvailability(deal);
        Deal updatedDeal = dealRepository.save(deal2);
        return dealMapper.toDTO(updatedDeal);
    }

    public DealDTO rejectDeal(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deal not found"));
        deal.setStatus("REJECTED");
        Deal deal2 = updatePropertyAvailability(deal);
        Deal updatedDeal = dealRepository.save(deal2);
        return dealMapper.toDTO(updatedDeal);
    }
    private Deal updatePropertyAvailability(Deal deal) {
        System.out.println(deal.getStatus());
        System.out.println(deal.getProperty().getId());
        if (Objects.equals(deal.getStatus(), "PENDING") || Objects.equals(deal.getStatus(), "APPROVED")) {
            propertyService.updateAvailability(deal.getProperty().getId(),false);
        } else if (Objects.equals(deal.getStatus(), "REJECTED")) {
            propertyService.updateAvailability(deal.getProperty().getId(),true);
        }
        return deal;
    }
}

