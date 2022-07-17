package com.consult.medical.mapper;

import com.consult.medical.dto.PatientStatusDto;
import com.consult.medical.entities.PatientStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientStatusMapper {
    PatientStatusMapper INSTANCE = Mappers.getMapper(PatientStatusMapper.class);
    @Mapping(source = "patientStatus.code", target = "code")
    @Mapping(source = "patientStatus.numberId", target = "numberId")
    PatientStatusDto toResponse(PatientStatus patientStatus);

    @Mapping(source = "code", target = "code")
    @Mapping(source = "numberId", target = "numberId")
    PatientStatus toSave(PatientStatusDto patientStatusDto);
}
