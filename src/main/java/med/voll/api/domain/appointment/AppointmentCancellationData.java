package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancellationData(
        @NotNull
        Long appointmentId,

        @NotNull
        CancellationReason reason) {
}
