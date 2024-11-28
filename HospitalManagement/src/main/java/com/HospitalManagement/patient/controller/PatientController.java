package com.HospitalManagement.patient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagement.doctor.exception.ResourceNotFoundException;
import com.HospitalManagement.patient.entity.Patient;
import com.HospitalManagement.patient.repo.PatientRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/patient")
public class PatientController {

	private PatientRepository patientRepository;

	public PatientController(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	@PostMapping
	public Patient create(@RequestBody Patient patient) {
		return patientRepository.save(patient);
	}

	@GetMapping
	public List<Patient> getAll() {
		return patientRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getById(@PathVariable long id)
	{
		Patient patient =patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		return ResponseEntity.ok(patient);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable long id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		patientRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Patient Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatientById(@PathVariable long id, @RequestBody Patient patientDetails) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));

		patient.setFname(patientDetails.getFname());
		patient.setLname(patientDetails.getLname());
		patient.setAge(patientDetails.getAge());
		patient.setBlood(patientDetails.getBlood());

		patient.setPrescription(patientDetails.getPrescription());
		patient.setDose(patientDetails.getDose());
		patient.setFees(patientDetails.getFees());
		patient.setUrgency(patientDetails.getUrgency());

		Patient savedPatient = patientRepository.save(patient);

		return ResponseEntity.ok(savedPatient);
	}

}
