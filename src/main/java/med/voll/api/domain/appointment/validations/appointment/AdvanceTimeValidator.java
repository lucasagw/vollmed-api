package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.AppointmentSchedulingData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("AdvanceTimeValidatorForScheduling")
public class AdvanceTimeValidator implements AppointmentSchedulingValidator {

    public void validate(AppointmentSchedulingData data) {

        var appointmentDate = data.date();
        var now = LocalDateTime.now();

        long minutes = Duration.between(now, appointmentDate)
                .toMinutes();

        if (minutes < 30) {
            throw new IllegalArgumentException("The appointment must be scheduled at least 30 minutes in advance");
        }

    }
}
