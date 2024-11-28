package com.HospitalManagement.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.doctor.entity.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{

}
