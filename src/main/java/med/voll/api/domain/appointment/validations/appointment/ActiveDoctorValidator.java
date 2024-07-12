package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.AppointmentSchedulingData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements AppointmentSchedulingValidator {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void validate(AppointmentSchedulingData data) {

        if (data.doctorId() == null) {
            return;
        }

        if (!doctorRepository.findActiveById(data.doctorId())) {
            throw new IllegalArgumentException("Appointment cannot be scheduled with an inactive doctor");
        }
    }
}
