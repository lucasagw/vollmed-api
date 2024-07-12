package med.voll.api.domain.appointment.validations.appointment;

import med.voll.api.domain.appointment.AppointmentSchedulingData;

import java.time.DayOfWeek;

public class ClinicalOpeningHoursValidator implements AppointmentSchedulingValidator {

    public void validate(AppointmentSchedulingData data) {

        var appointmentDate = data.date();

        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var beforeOpeningHours = appointmentDate.getHour() < 7;

        var afterClosingHours = appointmentDate.getHour() > 18;

        if (sunday || beforeOpeningHours || afterClosingHours) {
            throw new IllegalArgumentException("Clinic is closed on Sundays and outside of working hours");
        }


    }
}
