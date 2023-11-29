package com.example.agent.repository;

import com.example.agent.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByAvailableTrue();
}
