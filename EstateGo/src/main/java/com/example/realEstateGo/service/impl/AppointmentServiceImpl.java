package com.example.realEstateGo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realEstateGo.entity.Agent;
import com.example.realEstateGo.entity.Appointment;
import com.example.realEstateGo.entity.Customer;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.repository.AgentRepository;
import com.example.realEstateGo.repository.AppointmentRepository;
import com.example.realEstateGo.repository.CustomerRepository;
import com.example.realEstateGo.service.AppointmentService;
import com.example.realEstateGo.service.EmailNotificationService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
    private EmailNotificationService emailNotificationService;


	@Override
	public Appointment addNewAppointment(Appointment appointment) throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(appointment.getAppointmentCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Customer", " id", appointment.getAppointmentCustomerId()));

		Agent agent = agentRepository.findById(appointment.getAppointmentAgentId()).orElseThrow(
				() -> new ResourceNotFoundException("Agent", " id", appointment.getAppointmentAgentId()));

		appointment.setCustomer(customer);
		appointment.setAgent(agent);
		notifyNewCustomerEmail(customer,appointment);
		return appointmentRepository.save(appointment);
	}
	
	private void notifyNewCustomerEmail(Customer c,Appointment a) {
        String customerEmail = c.getEmail().trim();
        emailNotificationService.NewAppointmentEmail(customerEmail,c.getName(),a.getDate(),c.getAddress());
    }

	@Override
	public Appointment updateAppointment(int appointmentId, Appointment updatedAppointment)
			throws ResourceNotFoundException {
		Optional<Appointment> existingAppointmentOptional = appointmentRepository.findById(appointmentId);

		if (existingAppointmentOptional.isPresent()) {
			Appointment existingAppointment = existingAppointmentOptional.get();
			// Update fields of the existing appointment with the values from
			// updatedAppointment
			existingAppointment.setDescription(updatedAppointment.getDescription());
			existingAppointment.setDate(updatedAppointment.getDate());
			existingAppointment.setCustomer(updatedAppointment.getCustomer());
			existingAppointment.setAgent(updatedAppointment.getAgent());
			existingAppointment.setStatus(updatedAppointment.getStatus());

			return appointmentRepository.save(existingAppointment);
		} else {
			// Handle the case where the appointment with the given ID is not found
			throw new ResourceNotFoundException("Appointment", " id", appointmentId);
		}
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment getAppointmentById(int appointmentId) throws ResourceNotFoundException {
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);

		if (appointmentOptional.isPresent()) {
			return appointmentOptional.get();
		} else {
			// Handle the case where the appointment with the given ID is not found
			throw new ResourceNotFoundException("Appointment", " id", appointmentId);
		}
	}

	@Override
	public void deleteAppointment(int id) {
		// TODO Auto-generated method stub

	}
}
