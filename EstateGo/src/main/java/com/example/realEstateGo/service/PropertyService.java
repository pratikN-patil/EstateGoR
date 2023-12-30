package com.example.realEstateGo.service;

import java.util.List;

import com.example.realEstateGo.Dto.PropertyDTO;
import com.example.realEstateGo.entity.Property;
import com.example.realEstateGo.exception.ResourceNotFoundException;

public interface PropertyService {
	public Property addNewProperty(Property b);

	public Property updateProperty(int propertyId, Property updatedProperty) throws ResourceNotFoundException;

	public List<PropertyDTO> getAllProperty();

	public List<PropertyDTO> findPropertyByName(String propertyName) throws ResourceNotFoundException;

	List<PropertyDTO> findPropertyByCity(String propertyCity) throws ResourceNotFoundException;

}
