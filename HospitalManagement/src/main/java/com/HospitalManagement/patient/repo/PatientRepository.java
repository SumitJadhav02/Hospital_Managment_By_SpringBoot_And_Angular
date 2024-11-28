package com.HospitalManagement.patient.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
