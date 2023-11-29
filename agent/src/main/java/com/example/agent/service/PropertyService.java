package com.example.agent.service;

import com.example.agent.dto.PropertyDTO;
import com.example.agent.entity.Property;
import com.example.agent.mapper.PropertyMapper;
import com.example.agent.repository.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream()
                .map(propertyMapper::toDTO)
                .sorted(Comparator.comparing(PropertyDTO::getId).reversed())
                .collect(Collectors.toList());
    }

    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        return propertyMapper.toDTO(property);
    }

    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        Property property = propertyMapper.toEntity(propertyDTO);
        Property createdProperty = propertyRepository.save(property);
        return propertyMapper.toDTO(createdProperty);
    }

    public PropertyDTO updateProperty(PropertyDTO propertyDTO) {
        Property property = propertyMapper.toEntity(propertyDTO);
        Property updatedProperty = propertyRepository.save(property);
        return propertyMapper.toDTO(updatedProperty);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    public List<PropertyDTO> findAllByAvailableTrue() {
        return propertyRepository.findAllByAvailableTrue().stream()
                .map(propertyMapper::toDTO)
                .sorted(Comparator.comparing(PropertyDTO::getId).reversed())
                .collect(Collectors.toList());
    }
    public PropertyDTO updateAvailability(Long id, boolean available) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        property.setAvailable(available);
        Property updatedProperty = propertyRepository.save(property);
        return propertyMapper.toDTO(updatedProperty);
    }
}
