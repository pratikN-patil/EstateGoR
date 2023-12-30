package com.example.realEstateGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realEstateGo.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
