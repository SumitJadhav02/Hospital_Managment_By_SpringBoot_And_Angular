package com.HospitalManagement.doctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagement.doctor.entity.Appointment;
import com.HospitalManagement.doctor.exception.ResourceNotFoundException;
import com.HospitalManagement.doctor.repo.AppointmentRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@PostMapping
	public Appointment create(@RequestBody Appointment appointment)
	{
		return appointmentRepository.save(appointment);
	}
	
	@GetMapping
	public List<Appointment> getAll()
	{
		return appointmentRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));
        appointmentRepository.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Appointment Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
