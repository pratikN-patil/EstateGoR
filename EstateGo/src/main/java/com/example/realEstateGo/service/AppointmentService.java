package com.example.realEstateGo.service;

import java.util.List;

import com.example.realEstateGo.entity.Appointment;
import com.example.realEstateGo.exception.ResourceNotFoundException;

public interface AppointmentService {
	public Appointment addNewAppointment(Appointment a) throws ResourceNotFoundException;

	public Appointment updateAppointment(int id, Appointment b) throws ResourceNotFoundException;

	public List<Appointment> getAllAppointments();

	public Appointment getAppointmentById(int id) throws ResourceNotFoundException;

	public void deleteAppointment(int id);
}
