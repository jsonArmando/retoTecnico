package com.consult.medical.repository;

import com.consult.medical.entities.PatientStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientStatusRepository extends CrudRepository<PatientStatus, Integer> {
    List<PatientStatus> findByNumberId(Integer numberId);
}
