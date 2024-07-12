package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.AppointmentSchedulingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAnotherAppointmentAtSameTimeValidator implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentSchedulingData data) {
        var doctorHasAnotherAppointmentAtSameTime = appointmentRepository.existsByDoctorIdAndDateAndCancellationReasonIsNull(data.doctorId(), data.date());
        if (doctorHasAnotherAppointmentAtSameTime) {
            throw new IllegalArgumentException("Doctor already has another appointment scheduled at the same time");
        }
    }

}
