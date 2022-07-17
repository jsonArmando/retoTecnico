package com.consult.medical.exception;

import com.consult.medical.utils.PatientStatusError;
import lombok.Getter;

@Getter
public class PatientStatusException  extends Exception{
    private final PatientStatusError patientStatusError;
    public PatientStatusException(PatientStatusError patientStatusError){
        super(patientStatusError == null ? null : patientStatusError.getMessage()); //no cause, customer throws
        this.patientStatusError = patientStatusError;
    }
    public PatientStatusException(PatientStatusError patientStatusError, Exception exception){
        super(exception);
        this.patientStatusError =patientStatusError;
    }

}
