package com.example.realEstateGo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import com.example.realEstateGo.Dto.PropertyDTO;
import com.example.realEstateGo.entity.Agent;
import com.example.realEstateGo.entity.Property;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.repository.AgentRepository;
import com.example.realEstateGo.repository.PropertyRepository;
import com.example.realEstateGo.repository.ReviewsRepository;
import com.example.realEstateGo.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private ReviewsRepository reviewsRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public Property addNewProperty(Property p) {
		Agent a1 = agentRepository.findById(p.getPropertyAgentId()).get();
		p.setAgent(a1);
		return propertyRepository.save(p);
	}

	@Override
	public List<PropertyDTO> getAllProperty() {
		List<Property> properties = propertyRepository.findAll();
//		 return properties.stream().map(property -> modelMapper.map(property, PropertyDTO.class)).collect(Collectors.toList());
		return properties.stream().map(p -> modelMapper.map(p, PropertyDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<PropertyDTO> findPropertyByName(String propertyName) throws ResourceNotFoundException {
		Optional<Property> propertyOptional = propertyRepository.findByName(propertyName);

		if (propertyOptional.isPresent()) {
			return propertyOptional.stream().map(property ->modelMapper.map(propertyOptional,PropertyDTO.class)).collect(Collectors.toList());
		} else {
			throw new ResourceNotFoundException("Property", " id", propertyName);
		}
	}

	@Override
	public List<PropertyDTO> findPropertyByCity(String propertyCity) throws ResourceNotFoundException {
		Optional<Property> propertyOptional = propertyRepository.findByCity(propertyCity);

		if (propertyOptional.isPresent()) {
			return propertyOptional.stream().map(property ->modelMapper.map(propertyOptional,PropertyDTO.class)).collect(Collectors.toList());
		} else {
			throw new ResourceNotFoundException("Property", " id", propertyCity);
		}
	}

	@Override
	public Property updateProperty(int propertyId, Property updatedProperty) throws ResourceNotFoundException {
		Optional<Property> existingPropertyOptional = propertyRepository.findById(propertyId);

		if (existingPropertyOptional.isPresent()) {
			Property existingProperty = existingPropertyOptional.get();
			// Update fields of the existing property with the values from updatedProperty
			existingProperty.setName(updatedProperty.getName());
			existingProperty.setDescription(updatedProperty.getDescription());
			existingProperty.setPrice(updatedProperty.getPrice());
			existingProperty.setPropertyType(updatedProperty.getPropertyType());
			existingProperty.setPropertyImage(updatedProperty.getPropertyImage());
			existingProperty.setStatus(updatedProperty.getStatus());
			

			return propertyRepository.save(existingProperty);
		} else {
			// Handle the case where the property with the given ID is not found
			throw new ResourceNotFoundException("Property", " id", propertyId);
		}
	}

}
