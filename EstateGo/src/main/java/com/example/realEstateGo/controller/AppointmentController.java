package com.example.realEstateGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.realEstateGo.entity.Appointment;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.service.AppointmentService;
import com.example.realEstateGo.service.impl.AppointmentServiceImpl;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
//@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;

	@PostMapping("/appointment/addp")
	public Appointment addNewAppointment(@RequestBody Appointment a) throws ResourceNotFoundException {
		return appointmentService.addNewAppointment(a);
	}
	@GetMapping("/listAppointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable int appointmentId) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            Map<String, String> map = new HashMap<>();
            map.put("errorMessage", ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/appointment/update/{appointmentId}")
    public ResponseEntity<Object> updateAppointment(@PathVariable int appointmentId, @Valid @RequestBody Appointment updatedAppointment
    ) {
        try {
            Appointment appointment = appointmentService.updateAppointment(appointmentId, updatedAppointment);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            Map<String, String> map = new HashMap<>();
            map.put("errorMessage", ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }


//    @DeleteMapping("/{appointmentId}")
//    public String deleteAppointment(@PathVariable int appointmentId) {
//    	appointmentServiceImpl.deleteAppointment(appointmentId);
//        return "Appointment with id " + appointmentId + " deleted successfully.";
//    }
    
    
}
