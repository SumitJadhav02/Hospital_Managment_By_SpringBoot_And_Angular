package com.HospitalManagement.doctor.controller;

import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.HospitalManagement.doctor.entity.Medicine;
import com.HospitalManagement.doctor.exception.ResourceNotFoundException;
import com.HospitalManagement.doctor.repo.MedicineRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineRepository medicineRepository;


	@PostMapping
	public Medicine create(@RequestBody Medicine medicine)
	{
		return medicineRepository.save(medicine);
	}
	
	@GetMapping
	public List<Medicine> getAll()
	{
		return medicineRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable long id)
	{
		Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		return ResponseEntity.ok(medicine);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable long id)
	{
		Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		medicineRepository.delete(medicine);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Medicine Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medicine>updateMedicineById(@PathVariable long id, @RequestBody Medicine medicineDetails)
	{
		Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
		
		medicine.setId(medicineDetails.getId());
		medicine.setDrugName(medicineDetails.getDrugName());
		medicine.setStock(medicineDetails.getStock());
		
		Medicine savedMedicine = medicineRepository.save(medicine);
		
		return ResponseEntity.ok(savedMedicine);
	}

}
