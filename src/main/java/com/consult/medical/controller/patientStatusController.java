package com.consult.medical.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consult.medical.dto.PatientStatusDto;
import com.consult.medical.exception.PatientStatusException;
import com.consult.medical.service.PatientStatusService;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class patientStatusController {

    private PatientStatusService patientStatusService;

    @GetMapping(value ="/patient-status")
    public ResponseEntity<List<PatientStatusDto>> findPatientStatus() throws PatientStatusException{
        List<PatientStatusDto> patientStatusDtos =patientStatusService.findPatientStatus();
        
        if(!patientStatusDtos.isEmpty()){
            var response = new ResponseEntity<>(patientStatusDtos,HttpStatus.OK);
            return response;
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value ="/patient-id/{patientStatusId}")
    public ResponseEntity<List<PatientStatusDto>> findByNumberId(@PathVariable Integer patientStatusId) throws PatientStatusException{
        var response = new ResponseEntity<>(patientStatusService.findByNumberId(patientStatusId),HttpStatus.OK);
        return response;
    }
    @PostMapping(value ="/patient-create",consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPatientStatus(@Valid @RequestBody PatientStatusDto patientStatusDto) throws PatientStatusException{
        patientStatusService.createPatientStatus(patientStatusDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
