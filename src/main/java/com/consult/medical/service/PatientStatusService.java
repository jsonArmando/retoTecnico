package com.consult.medical.service;

import com.consult.medical.dto.PatientStatusDto;
import com.consult.medical.entities.PatientStatus;
import com.consult.medical.exception.PatientStatusException;
import com.consult.medical.mapper.PatientStatusMapper;
import com.consult.medical.repository.PatientStatusRepository;
import com.consult.medical.utils.PatientStatusError;

import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientStatusService {
    private final PatientStatusMapper patientStatusMapper;
    private final PatientStatusRepository patientStatusRepository;
    @Value("${low.pathology}")
    private String low;
    @Value("${medium.pathology}")
    private String medium;
    @Value("${high.pathology}")
    private String high;
    @Value("${nivel.pathology}")
    private String nivel;

    public List<PatientStatusDto> findPatientStatus() throws PatientStatusException{
        final var lsPatientStatus = patientStatusRepository.findAll();
        List<PatientStatus> lsPatientStatusList = StreamSupport.stream(lsPatientStatus.spliterator(), false)
    .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(lsPatientStatusList)){
            throw new PatientStatusException(PatientStatusError.RESOURCE_PATIENT_STATUS_NOT_FOUND);
        }
        var lsPatientStatusDto = lsPatientStatusList.stream().map(patientStatusMapper::toResponse).collect(Collectors.toList());
    return lsPatientStatusDto;
    }

    public List<PatientStatusDto> findByNumberId(Integer numberId) throws PatientStatusException{
        final var lsPatientStatus = patientStatusRepository.findByNumberId(numberId);
        if(CollectionUtils.isEmpty(lsPatientStatus)){
            throw new PatientStatusException(PatientStatusError.RESOURCE_PATIENT_STATUS_NOT_FOUND);
        }
        var lsPatientStatusDto = lsPatientStatus.stream().map(patientStatusMapper::toResponse).collect(Collectors.toList());
        return lsPatientStatusDto;
    }

    @Transactional
    public void createPatientStatus(PatientStatusDto patientStatusDto) throws PatientStatusException{
        log.debug("createPatientStatus (create prms)");
        var patientStatus = patientStatusMapper.toSave(patientStatusDto);
        boolean active = true;

        List<PatientStatus> list = patientStatusRepository.findByNumberId(patientStatus.getNumberId());
        
        log.error("validate patient the risk disease");
        if(!CollectionUtils.isEmpty(list)){
            if(list.size()>PatientStatusError.MAX_DUPLICATE.getCode()){
                throw new PatientStatusException(PatientStatusError.ERROR_UNIQUE);
            }else{
                throw new PatientStatusException(PatientStatusError.ERROR_EXIST_REQUEST);
            }
        }

        if(patientStatus.getSugar() >100 || patientStatus.getFat()>100 || patientStatus.getOxygen()>100){
            throw new PatientStatusException(PatientStatusError.ERROR_PERCENTAGE);
        }

        if(patientStatus.getSugar()>70 && patientStatus.getFat()>85.5 && patientStatus.getOxygen()<60){
            
            patientStatus.setStatus(PatientStatusError.HIGH_RISK_DISEASE.getMessage());
            patientStatus.setDescription(high);
            patientStatus.setActive(active);

        }else if((patientStatus.getSugar()<=70 && patientStatus.getSugar()<50) &&
            (patientStatus.getFat()<=85.5 && patientStatus.getFat()>62.2) &&
            (patientStatus.getOxygen()>=60 && patientStatus.getOxygen()<70)){

                patientStatus.setStatus(PatientStatusError.MEDIUM_RISK_DISEASE.getMessage());
                patientStatus.setDescription(medium);
                patientStatus.setActive(active);

        }else if(patientStatus.getSugar()<=50 && patientStatus.getFat()<=62.2 && patientStatus.getOxygen()>=70){
            patientStatus.setStatus(PatientStatusError.LOW_RISK_DISEASE.getMessage());
            patientStatus.setDescription(low);
            patientStatus.setActive(active);
        }else{
            patientStatus.setStatus(PatientStatusError.PATIENT_NIVEL_DISEASE.getMessage());
            patientStatus.setDescription(nivel);
            patientStatus.setActive(false);
        }

        patientStatusRepository.save(patientStatus);
    }
}
