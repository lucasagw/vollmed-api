package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.AppointmentSchedulingData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentSchedulingValidator {

    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentSchedulingData data) {
        var isPatientActive = patientRepository.findActiveById(data.patientId());
        if (!isPatientActive) {
            throw new IllegalArgumentException("Appointment cannot be scheduled with an inactive patient");
        }
    }
}