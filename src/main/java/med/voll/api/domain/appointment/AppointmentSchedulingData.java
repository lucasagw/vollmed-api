package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentSchedulingData(
        @Positive
        Long doctorId,

        @NotNull
        @Positive
        Long patientId,

        @Future
        @NotNull
        LocalDateTime date,

        Specialty specialty,

        String time,

        String description) {
}
