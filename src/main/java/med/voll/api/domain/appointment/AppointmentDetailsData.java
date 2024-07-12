package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsData(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date) {

    public AppointmentDetailsData(Appointment save) {
        this(save.getId(), save.getDoctor().getId(), save.getPatient().getId(), save.getDate());
    }
}
