package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.AppointmentCancellationData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("AdvanceTimeValidatorForCancellation")
public class AdvanceTimeValidator implements AppointmentCancellationValidator {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCancellationData data) {
        var appointment = repository.getReferenceById(data.appointmentId());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new IllegalArgumentException("Appointments can only be cancelled with a minimum of 24 hours notice!");
        }
    }
}