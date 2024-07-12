package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentCancellationData;
import med.voll.api.domain.appointment.AppointmentSchedulingData;
import med.voll.api.domain.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment(@RequestBody @Valid AppointmentSchedulingData appointmentSchedulingData) {

        return ResponseEntity.ok(appointmentService.scheduleAppointment(appointmentSchedulingData));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancel(@RequestBody @Valid AppointmentCancellationData data) {

        appointmentService.cancel(data);

        return ResponseEntity.noContent().build();
    }


}
