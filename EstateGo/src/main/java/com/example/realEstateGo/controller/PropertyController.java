package com.example.realEstateGo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.realEstateGo.Dto.PropertyDTO;
import com.example.realEstateGo.entity.Property;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.service.PropertyService;
import jakarta.validation.Valid;

@RestController
//@RequestMapping("/property")
public class PropertyController {
	@Autowired
	PropertyService propertyService;

//	@PreAuthorize("hasAuthority('ROLE_Agent')")
	@PostMapping("/propertyAdd")
	public Property addNewProperty(@RequestBody Property p) {
		System.out.println("Received a request to add property: " + p);
		return propertyService.addNewProperty(p);
	}

	@GetMapping("/propertyList")
//	@PreAuthorize("hasAuthority('ROLE_Customer')")
	public ResponseEntity<List<PropertyDTO>> getAllProperty() {
		List<PropertyDTO> properties = propertyService.getAllProperty();

		if (properties.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(properties, HttpStatus.OK);
		}
	}

	@PutMapping("/{propertyId}")
//	@PreAuthorize("hasAuthority('ROLE_AGENT')")
	public ResponseEntity<Object> updateProperty(@PathVariable int propertyId,
			@Valid @RequestBody Property updatedProperty) {
		try {
			Property property = propertyService.updateProperty(propertyId, updatedProperty);
			return new ResponseEntity<>(property, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/property/name/{propertyName}")
//	@PreAuthorize("hasAuthority('ROLE_AGENT')")
	public ResponseEntity<Object> findPropertyByName(@PathVariable String propertyName) {
		try {
			List<PropertyDTO> property = propertyService.findPropertyByName(propertyName);
			return new ResponseEntity<>(property, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/property/{propertyAddress}")
//	@PreAuthorize("hasAuthority('ROLE_Customer')")
	public ResponseEntity<Object> findPropertyByCity(@PathVariable String propertyCity) {
		try {
			List<PropertyDTO> property = propertyService.findPropertyByCity(propertyCity);
			return new ResponseEntity<>(property, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

}
