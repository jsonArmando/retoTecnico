package com.consult.medical.mapper;

import com.consult.medical.dto.PatientStatusDto;
import com.consult.medical.entities.PatientStatus;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class PatientStatusMapperTest {

    PatientStatus patientStatus = new PatientStatus
    (1,"Artritis",123,"Jose Armando","Son Rojas",90,87,67,"Alto",true);
    PatientStatusDto patientStatusDto = new PatientStatusDto
    (1,"Artritis",123,"Jose Armando","Son Rojas",90,87,67,"Alto",true);
    @Test
    public void PatientToResponse(){
        PatientStatusDto patientStatusDto = PatientStatusMapper.INSTANCE.toResponse(patientStatus);
        assertThat(patientStatusDto).isNotNull();
        assertThat(patientStatusDto.getCode()).isEqualTo(1);
        assertThat(patientStatusDto.getDescription()).isEqualTo("Artritis");
        assertThat(patientStatusDto.getOxygen()).isEqualTo(67);
    }

    @Test
    public void PatientToSave(){
        PatientStatus patientStatus = PatientStatusMapper.INSTANCE.toSave(patientStatusDto);
        assertThat(patientStatus).isNotNull();
        assertThat(patientStatus.getCode()).isEqualTo(1);
        assertThat(patientStatus.getDescription()).isEqualTo("Artritis");
        assertThat(patientStatus.getOxygen()).isEqualTo(67);

    }
}
