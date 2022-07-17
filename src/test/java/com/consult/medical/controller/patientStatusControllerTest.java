package com.consult.medical.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.consult.medical.dto.PatientStatusDto;
import com.consult.medical.exception.PatientStatusException;
import com.consult.medical.service.PatientStatusService;

@SpringBootTest(classes = patientStatusControllerTest.class)
public class patientStatusControllerTest {

    @InjectMocks
    patientStatusController patientStatusController;
    @Mock
    PatientStatusService patientStatusService;

    private PatientStatusDto dto;
    private List<PatientStatusDto> dtos;

    private Integer numberId = 4567;

    @BeforeEach
    public void init(){
        dto = new PatientStatusDto();
        dtos = new ArrayList<>();
        dto.setNumberId(4567);
        dto.setNames("Miguel Aurelio");
        dto.setSurnames("Sanchéz Sharma");
        dto.setSugar(75);
        dto.setFat(87);
        dto.setOxygen(45);
        dtos.add(dto);
    }

    @Test
    public void createPatientStatusTest() throws PatientStatusException{
        doNothing().when(patientStatusService).createPatientStatus(dto);
        ResponseEntity<Object> response = patientStatusController.createPatientStatus(dto);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void findByNumberIdTest() throws PatientStatusException{
        doReturn(dtos).when(patientStatusService).findByNumberId(numberId);
        ResponseEntity<List<PatientStatusDto>> list = patientStatusController.findByNumberId(numberId);
        assertEquals(dtos.get(0).getNumberId(),list.getBody().get(0).getNumberId());
    }
    
}


