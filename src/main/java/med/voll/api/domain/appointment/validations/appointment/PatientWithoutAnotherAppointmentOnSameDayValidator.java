package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.AppointmentSchedulingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutAnotherAppointmentOnSameDayValidator implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentSchedulingData data) {
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var patientHasAnotherAppointmentOnSameDay = repository.existsByPatientIdAndDateBetween(data.patientId(), firstHour, lastHour);
        if (patientHasAnotherAppointmentOnSameDay) {
            throw new IllegalArgumentException("Patient already has an appointment scheduled on this day");
        }
    }
}
