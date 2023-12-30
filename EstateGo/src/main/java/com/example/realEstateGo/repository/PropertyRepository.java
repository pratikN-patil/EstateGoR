package com.example.realEstateGo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realEstateGo.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
	Optional<Property> findByName(String propertyName);
	Optional<Property> findByCity(String propertyCity);
}
