package com.example.agent.repository;

import com.example.agent.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findAllByBuyerId(Long buyerId);
}
