package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndDateAndCancellationReasonIsNull(Long id, LocalDateTime date);


    boolean existsByPatientIdAndDateBetween(Long id, LocalDateTime firstHour, LocalDateTime lastHour);
}
