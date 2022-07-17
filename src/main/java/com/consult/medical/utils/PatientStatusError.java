package com.consult.medical.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@ToString
public enum PatientStatusError {
    UNEXPECTED_ERROR(0, "Unexpected error."),
    BAD_REQUEST_INVALID_ARGUMENTS(1,"Validation error. Detail in the 'errors' field."),
    BAD_REQUEST_INVALID_PARAMETERS(2, "Missing required parameter."),
    ENDPOINT_NOT_FOUND(3, "Endpoint not found."),
    RESOURCE_PATIENT_STATUS_NOT_FOUND(100, "Patient status resource not found."),
    HIGH_RISK_DISEASE(10,"HIGH risk disease."),
    MEDIUM_RISK_DISEASE(11,"MEDIUM risk disease."),
    LOW_RISK_DISEASE(11,"LOW risk disease"),
    PATIENT_NIVEL_DISEASE(11,"Patient Nivel disease"),
    MAX_DUPLICATE(1, "Evaluates the duplicate value."),
    ERROR_UNIQUE(2,"The patient is duplicated."),
    ERROR_EXIST_REQUEST(3,"The patient is already registered."),
    ERROR_PERCENTAGE(4,"Percentage error should not be greater than 100%");

    private final Integer code;
    private final String message;

    public HttpStatus getStatus() {
        switch (this) {
            case BAD_REQUEST_INVALID_ARGUMENTS:
            case BAD_REQUEST_INVALID_PARAMETERS:
                return HttpStatus.BAD_REQUEST;
            case ENDPOINT_NOT_FOUND:
            case RESOURCE_PATIENT_STATUS_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case HIGH_RISK_DISEASE:
            case MEDIUM_RISK_DISEASE:
            case LOW_RISK_DISEASE:
            case PATIENT_NIVEL_DISEASE:
            case ERROR_PERCENTAGE:
            case ERROR_UNIQUE:
            case MAX_DUPLICATE:
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
